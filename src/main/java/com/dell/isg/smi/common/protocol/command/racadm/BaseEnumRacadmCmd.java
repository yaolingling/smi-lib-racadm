/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.isg.smi.commons.utilities.xml.XmlHelper;
import com.dell.isg.smi.common.protocol.command.cmc.entity.RacadmCredentials;
import com.dell.isg.smi.common.protocol.racadm.RacadmCommandHelper;

public abstract class BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(BaseEnumRacadmCmd.class);
    private Document document = null;
    private String command = null;
    private RacadmCredentials credentials = null;
    private String ipAddr;


    // ------------ constructors -----------------------------

    protected BaseEnumRacadmCmd(String command, String ipAddr, String userName, String passwd, boolean bCertCheck) {
        this.credentials = new RacadmCredentials();
        this.credentials.setUserName(userName);
        this.credentials.setPassword(passwd);
        this.credentials.setCertificateCheck(bCertCheck);
        this.ipAddr = ipAddr;
        this.command = command;
    }


    // -------------- retrieval methods ---------------------

    protected void setCommand(String command) {
        this.document = null;
        this.command = command;
    }


    protected Document getDocument() throws Exception {
        if (null == this.document) {
            this.document = getRacadmCommand(this.ipAddr, credentials.getUserName(), credentials.getPassword(), this.command, credentials.isCertificateCheck());
            if (null == this.document) {
                logger.debug("document is null");
            }
        }
        // showXml(this.document);
        return this.document;
    }


    protected String getResult() throws Exception {
        return getRacadmCommandString(this.ipAddr, credentials.getUserName(), credentials.getPassword(), this.command, credentials.isCertificateCheck());

    }


    private Document getRacadmCommand(String chassisIP, String chassisUserName, String chassisPasswd, String command, boolean check) throws Exception {
        RacadmCommandHelper racadm = new RacadmCommandHelper(chassisIP, chassisUserName, chassisPasswd, check);
        Document doc = racadm.sendRacadmCommand(command);
        return doc;
    }


    private String getRacadmCommandString(String chassisIP, String chassisUserName, String chassisPasswd, String command, boolean check) throws Exception {
        RacadmCommandHelper racadm = new RacadmCommandHelper(chassisIP, chassisUserName, chassisPasswd, check);
        Document doc = racadm.sendRacadmCommand(command);
        return racadm.toString(doc);
    }


    // --------------------- Xpath and parsing methods --------------------------

    /**
     * use to filter the results, then parse them into a nodelist return type
     * 
     * @param xpathExpressionString the string for an xpath expression
     * @return Class<T> the entity class, (i.e. foo.class)
     * @throws Exception
     */
    protected <T> List<T> filterUsingXpathForNodeList(Class<T> clazz, String xpathExpressionString) throws Exception {
        List<T> list = null;
        if (null != this.getDocument()) {
            NodeList nodeList = null;

            // filter the response using xpath
            Object result = filterUsingXpathForObject(xpathExpressionString, XPathConstants.NODESET);
            if (null != result) {
                nodeList = (NodeList) result;
                list = xmlToEntity(clazz, nodeList);
            }
        }
        return list;
    }


    /**
     * use to filter the results, then parse them into a node return type
     * 
     * @param xpathExpressionString the string for the xpath expression
     * @return Class<T> the entity class, (i.e. foo.class)
     * @throws Exception
     */
    protected <T> T filterUsingXpathforNode(Class<T> clazz, String xpathExpressionString) throws Exception {
        T returnValue = null;
        if (null != this.getDocument()) {
            Node node = null;
            // filter the response using xpath
            Object result = filterUsingXpathForObject(xpathExpressionString, XPathConstants.NODE);
            if (null != result) {
                node = (Node) result;
                returnValue = xmlToEntity(clazz, node);
            }
        }
        return returnValue;
    }


    /**
     * Use to filter document results and get a number return type
     * 
     * @param xpathExpressionString the string for an xpath expression
     * @return Double
     * @throws Exception
     */
    protected Double filterUsingXpathForNumber(String xpathExpressionString) throws Exception {
        Double number = null;
        if (null != this.getDocument()) {
            Object result = filterUsingXpathForObject(xpathExpressionString, XPathConstants.NUMBER);
            if (null != result) {
                number = (Double) result;
            }
        }
        return number;
    }


    /**
     * Filters the document and returns an object
     * 
     * @param xpathExpressionString the string for an xpath expression
     * @param xpathConstants a constant of tye XpathConstants -- determines the query type (number, nodeset, etc.)
     * @return an object
     * @throws Exception
     */
    protected Object filterUsingXpathForObject(String xpathExpressionString, QName xpathConstants) throws Exception {
        Object result = null;
        if (null != this.getDocument()) {
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            XPathExpression expression = xpath.compile(xpathExpressionString);
            if (null != expression) {
                result = expression.evaluate(this.getDocument(), xpathConstants);
            }
        }
        return result;
    }


    protected <T> List<T> xmlToEntity(Class<T> clazz, NodeList nodeList) throws Exception {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            T entity = xmlToEntity(clazz, nodeList.item(i));
            if (null != entity) {
                list.add(entity);
            }
        }
        return list;
    }


    @SuppressWarnings("unchecked")
    protected <T> T xmlToEntity(Class<T> clazz, Node node) throws Exception {
        T entity = null;
        if ((null != node) && (Node.ELEMENT_NODE == node.getNodeType())) {
            entity = (T) XmlHelper.xmlToObject(node, clazz);
        }
        return entity;
    }


    // --------------- for development use -------------------
    protected String showXml(Document doc) {
        String output = null;
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            tf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            output = writer.getBuffer().toString().replaceAll("\n|\r", "");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return output;
    }

}
