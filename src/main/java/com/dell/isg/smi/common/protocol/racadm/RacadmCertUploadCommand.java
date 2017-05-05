/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.racadm;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.dell.isg.smi.common.protocol.http.HttpConnection;
import com.dell.isg.smi.common.protocol.util.ExceptionUtilities;

public class RacadmCertUploadCommand {

    private String ipAddress = null;
    private String userName = null;
    private String passwd = null;
    private boolean bCertcheck = false;
    private HttpConnection httpConnection = null;
    private boolean bShowCmdInErrorMsg = true;
    private String sCmd = null;
    private boolean bRetry = false;
    private final int CGI_FILE_TYPE_ONLY = 0;
    private static final String GETIDRACINFO = "getconfig -g idRacInfo";
    private static final String SSLCERTUPLOAD = "sslcertupload -f sslcertfile -t ";

    private int iWaitTimeInSecond = 0;


    public void setShowCmdInErrorMsg(boolean b) {
        bShowCmdInErrorMsg = b;
    }

    private static final Logger logger = LoggerFactory.getLogger(RacadmCertUploadCommand.class);
    private static Map<String, Semaphore> IPHash = new HashMap<String, Semaphore>();


    public RacadmCertUploadCommand(String ipAddress_, String userName_, String passwd_, boolean bCertcheck_) {
        ipAddress = ipAddress_;
        userName = userName_;
        passwd = passwd_;
        bCertcheck = bCertcheck_;

    }


    public void setWaitTime(int Seconds) {
        iWaitTimeInSecond = Seconds;
    }

    static HostnameVerifier hv = new HostnameVerifier() {

        @Override
        public boolean verify(String urlHostName, SSLSession session) {
            return true;
        }
    };


    private void initializeSecurity() {
        try {
            httpConnection.setTrustManager();
            httpConnection.setHostnameVerifier(hv);
        } catch (KeyManagementException e) {
            logger.error(e.getMessage(), e);
            ExceptionUtilities.handleRuntimeCoreException(200503, e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
            ExceptionUtilities.handleRuntimeCoreException(200503, e.getMessage());
        }

    }


    // get the session ID from response.
    // response is of fomat <?xml version="1.0" encoding="UTF-8"?><LOGIN><RESP><RC>0x0</RC><SID>1473387203</SID></RESP></LOGIN>
    // Following are the possible error codes
    // RC_OK = 0
    // RC_INVALID_ARGS = 1
    // RC_SYSCALL_FAILED = 2 /* check errno for more info */
    // RC_NO_MEMORY = 3
    // RC_NO_RAWDEVICE = 4
    // RC_VFLASH_NOTREADY = 5
    // RC_INTERNAL_ERROR = 6
    // RC_READ_ERROR = 7
    // RC_WRITE_ERROR = 8
    // RC_SEEK_FAILED = 9
    // RC_FILE_OPEN_FAILED = 10
    // RC_VFLASH_ENUM_FAILED = 11
    // RC_BUSY_TRYLATER = 12
    // RC_USER_QUIT_EARLY = 13
    private String getSidFromResponse(String response) {
        // Response should contain <SID>xxx</SID>, which is our session id
        Pattern o = Pattern.compile("<SID>(\\w+)</SID>|<RC>(\\w+)</RC>");
        int rc = -1;
        String sid = null;
        Matcher m = o.matcher(response);
        while (m.find()) {
            if (m.group().startsWith("<SID>")) {
                sid = m.group(1);
            } else if (m.group().startsWith("<RC>")) {
                String s = m.group(2);
                s = s.replace("0x", "");
                rc = Integer.parseInt(s, 16);

            }
        }
        if (rc != 0 || sid == null) {
            logger.error("Failed to get session ID. Please check login/pwd. Response = " + response);
            ExceptionUtilities.handleRuntimeCoreException(200501);
        }
        return sid;
    }

    // check output from racamd command
    // output is of format <?xml version="1.0"
    // encoding="UTF-8"?><EXEC><RESP><RC>0x0</RC><OUTPUTLEN>0x5c2</OUTPUTLEN><CMDRC>0x0</CMDRC><CMDOUTPUT> sample command response
    // </CMDOUTPUT></RESP></EXEC>


    // Setting of creds may fail with the follwoing error. Add retry around logic.
    // ><EXEC><RESP><RC>0x0</RC><OUTPUTLEN>0x3e</OUTPUTLEN><CMDRC>0x6201</CMDRC><CMDOUTPUT>A previous operation is in progress, please try again later.
    // </CMDOUTPUT></RESP></EXEC>

    private void checkResponse(String response) {
        // System.out.println( "Response = " + response);
        // Response should contain <SID>xxx</SID>, which is our session id
        Pattern o = Pattern.compile("<RC>(\\w+)</RC>|<CMDRC>(\\w+)</CMDRC>");
        int rc = -1;
        int cmdrc = -1;
        Matcher m = o.matcher(response);
        while (m.find()) {
            if (m.group().startsWith("<CMDRC>")) {
                String s = m.group(2);
                s = s.replace("0x", "");
                cmdrc = Integer.parseInt(s, 16);
            } else if (m.group().startsWith("<RC>")) {
                String s = m.group(1);
                s = s.replace("0x", "");
                rc = Integer.parseInt(s, 16);
            }
        }

        if (cmdrc == 0xC || cmdrc == 0x6201) // RC_BUSY_TRYLATER as per RACADM server.
        {
            bRetry = true;
        } else {
            bRetry = false;
        }

        if (rc != 0 || cmdrc != 0) {
            String sValue = "";
            try {
                sValue = StringUtils.substringBetween(response, "<CMDOUTPUT>", "</CMDOUTPUT>");
            } catch (Exception e) {
            }

            logger.error("Racadm command failed. Response = " + response);
            if (this.sCmd != null && bShowCmdInErrorMsg) {
                ExceptionUtilities.handleRuntimeCoreException(200269, this.sCmd, sValue);
            } else {
                ExceptionUtilities.handleRuntimeCoreException(200502, sValue);
            }

        }
    }


    public Document sendRacadmCommand(String fileName, int certType) throws Exception {
        Document doc = null;
        boolean bStop = false;
        int iRetryCount = 0;

        // a Chassis allows max. of 4 racadm sessions.
        // Using this logic to allow only 1 racadm session for a particular chassis( locking by chassis IP ).
        Semaphore s = null;
        synchronized (IPHash) {
            s = IPHash.get(ipAddress);
            if (s == null) {
                s = new Semaphore(1, true);
                IPHash.put(ipAddress, s);
            }
        }
        s.acquireUninterruptibly(); // acquire lock.

        // boolean bSetNicCmd = false;

        // if( sCmd.indexOf( "setniccfg") >= 0 )
        // {
        // bSetNicCmd = true;
        // }

        // if( bSetNicCmd )
        // {
        // try
        // {
        // Thread.sleep( 5000 );
        // }
        // catch( Exception e){}
        // }

        while (bStop == false) {
            try {
                doc = sendCommand(fileName, certType);
                bStop = true; // no exceptions....just exit from loop

                if (iWaitTimeInSecond > 0) // This is needed for CMC to set the username/password on the IOA
                {
                    try {
                        Thread.sleep(iWaitTimeInSecond * 1000);
                    } catch (Exception e) {
                    }
                }

                // if( bSetNicCmd )
                // {
                // try
                // {
                // Thread.sleep( 5000 );
                // }
                // catch( Exception e){}
                // }

                s.release(); // release lock
            } catch (Exception e) {
                if (bRetry == true) {
                    iRetryCount = iRetryCount + 1;
                    if (iRetryCount > 5) // give up after 5 min.
                    {
                        s.release(); // release lock
                        throw e;
                    } else {
                        bRetry = false;
                        try {
                            Thread.sleep(60000); // retry in a minute
                        } catch (Exception e1) {
                        }
                        logger.info("Racadm command retry #" + iRetryCount);
                    }
                } else {
                    s.release(); // release lock
                    throw e;
                }
            } // end catch block
        } // end while loop

        return doc;
    }


    private Document sendCommand(String certFilePath, int certType) throws IOException, SOAPException, JAXBException, SAXException, ParserConfigurationException, TransformerConfigurationException, TransformerException {

        String operation = "login";
        String destination = "https://" + ipAddress + "/cgi-bin/" + operation;
        String requestBody = "<LOGIN><REQ><USERNAME>" + userName + "</USERNAME><PASSWORD>" + passwd + "</PASSWORD></REQ></LOGIN>";

        try {
            httpConnection = new HttpConnection(destination, ipAddress, bCertcheck);
            initializeSecurity();

            // send request to get a session ID
            String sessionID = httpConnection.sendHttpRacadm(null, requestBody);
            // System.out.println( "Raw response Session ID: " + sessionID);

            sessionID = getSidFromResponse(sessionID);
            // System.out.println( "SessionID = " + sessionID);

            // Transfer the filecontent to RAC
            // TempDestfileName, content length, type and file content is the requestbody
            operation = "putfile";
            destination = "https://" + ipAddress + "/cgi-bin/" + operation;

            requestBody = readContents(certFilePath);

            byte[] contLength = intToBytes(requestBody.length());

            byte[] type = intToBytes(CGI_FILE_TYPE_ONLY);

            // Temp file Name for CMC
            String destiNationFileName = String.format("%-32s", "RACSSLCERT1");

            int contetLength = destiNationFileName.length() + requestBody.length() + contLength.length + type.length;

            ByteBuffer buf = ByteBuffer.allocate(contetLength);

            buf.put(destiNationFileName.getBytes());
            buf.put(contLength);
            buf.put(type);
            buf.put(requestBody.getBytes());

            httpConnection = new HttpConnection(destination, ipAddress, bCertcheck);
            initializeSecurity();

            String response1 = httpConnection.sendHttpPutfile(sessionID, buf.array());
            // This operation might not be needed.
            operation = "exec";
            destination = "https://" + ipAddress + "/cgi-bin/" + operation;

            requestBody = "<EXEC><REQ><CMDINPUT>racadm " + GETIDRACINFO + "</CMDINPUT><MAXOUTPUTLEN>0x0fff</MAXOUTPUTLEN></REQ></EXEC>";

            httpConnection = new HttpConnection(destination, ipAddress, bCertcheck);
            initializeSecurity();

            response1 = httpConnection.sendHttpRacadm(sessionID, requestBody);

            // Execute racadm sslcertupload command.

            operation = "exec";
            destination = "https://" + ipAddress + "/cgi-bin/" + operation;

            requestBody = "<EXEC><REQ><CMDINPUT>racadm " + SSLCERTUPLOAD + certType + "</CMDINPUT><MAXOUTPUTLEN>0x0fff</MAXOUTPUTLEN></REQ></EXEC>";
            sCmd = "sslcertupload -t 2 -f sslcertfile";

            httpConnection = new HttpConnection(destination, ipAddress, bCertcheck);
            initializeSecurity();

            String response = httpConnection.sendHttpRacadm(sessionID, requestBody);

            operation = "logout";
            destination = "https://" + ipAddress + "/cgi-bin/" + operation;
            httpConnection = new HttpConnection(destination, ipAddress, bCertcheck);
            initializeSecurity();
            response1 = httpConnection.sendHttpRacadm(sessionID, null);

            logger.debug("Raw response logout: " + response1);
            // System.out.println( "Raw response logout: " + response1 );

            checkResponse(response);

            String result = null;
            result = RacadmXMLParser.convertRacadmXMLOutputToText(response); // this returns just the raw racadm without the XML tags

            // Convert to a XML doc
            RacadmXMLParser myparser = new RacadmXMLParser();
            org.w3c.dom.Document doc = myparser.parseRacadmTextOutput(sCmd, result); // this converts the raw racadm output to a XML
                                                                                     // doc

            return null;
        } finally {
        }
    }


    private byte[] intToBytes(final int i) {
        // four bytes for length
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putInt(i);
        return bb.array();
    }


    private String readContents(String fileName) throws IOException {
        String input = "";
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
            input = IOUtils.toString(inputStream);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return input;
    }

}
