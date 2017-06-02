/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.racadm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.dell.isg.smi.commons.utilities.xml.XmlHelper;
import com.dell.isg.smi.common.protocol.util.ExceptionUtilities;

/**
 * Parse XML Output obtained by running racadm commands. Consumes an xml file which has categories of different output formatting
 * 
 * TODO: 1. Handle racadm errors 2. Handle connection issues 3. Handle simultaneous requests
 * 
 * @author Prashanth_Hegde
 * @version 1.0
 * @category SKYHAWK - POC
 * 
 */
public class RacadmXMLParser {

    // private static final InputStream racadmCommandType = "resources/wsdl/wsman/racadmCommands.xml";
    private Document dom;
    private Element rootElement;


    public RacadmXMLParser() throws ParserConfigurationException {
        dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        rootElement = dom.createElement("root");
    }


    /**
     * Parse racadm outputs for commands which are formatted with tab characters. Example: getflexaddr
     * 
     * TODO: Replace throws Exception with some meaningful Exception
     * 
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private void parseRacadmTableOutput(String racadmOutput) throws ParserConfigurationException, IOException {
        Scanner scanner = new Scanner(racadmOutput);

        Element entryElement = null;
        List<String> elementList = new ArrayList<String>();
        List<String> valueList = new ArrayList<String>();

        String section = "";
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine().toString();
            if (currentLine.isEmpty())
                continue;

            Scanner lineScanner = new Scanner(currentLine);

            // determine if it is the square bracket header section info
            if (lineScanner.findInLine("\\[") != null && lineScanner.findInLine("\\]") != null) {
                String delims = "[\\[\\]:]+";
                String[] tokens = currentLine.split(delims);
                String elem = null;
                for (String token : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(token)) {
                        elem = token.replace(" ", "_");
                        break;
                    }
                }
                section = elem;
                continue;
            }

            // not the square bracket header section
            if (lineScanner.findInLine("<") != null && lineScanner.findInLine(">") != null) {
                // If first line contains tags like <IO> etc, consider them
                // as elements of the xml document

                elementList.clear();
                String delims = "[<>\t#]+";
                String[] tokens = currentLine.split(delims);
                for (String str : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(str)) {
                        elementList.add(str.replace(" ", "_"));
                    }
                }
                continue;
                // System.out.println("");
            }

            // if we make it here, we have a row of data.
            // start our entry
            entryElement = dom.createElement("entry");

            String regex = "\\([^)]*\\)";
            currentLine = currentLine.replaceAll(regex, "");
            // String delims = "[\t]+";
            String delims = ("\\s\\s+");
            // String delims = "[\s{2,}]+"
            Element childElement = null;
            valueList.clear();
            String[] tokens = currentLine.split(delims);
            for (String token : tokens) {
                valueList.add(token.trim());
            }

            while (valueList.size() < elementList.size())
                valueList.add(" ");

            if (valueList.size() != elementList.size()) {
                throw new ParserConfigurationException();
            }

            for (int i = 0; i < elementList.size(); i++) {
                String element = elementList.get(i);
                String value = valueList.get(i);

                // System.out.format("\nElement = %s\t\tValue = %s",
                // element, value);
                childElement = dom.createElement(element);
                childElement.setTextContent(value);
                entryElement.appendChild(childElement);
            }

            // add section info (if present) to the element and close the element
            if ((null != section) && !section.isEmpty()) {
                entryElement.setAttribute("section", section);
            }
            rootElement.appendChild(entryElement);

        }
        dom.appendChild(rootElement);
    }


    /**
     * SPECIAL CASE... Parse racadm outputs for commands which are formatted with tab characters. Example: getflexaddr
     * 
     * TODO: Replace throws Exception with some meaningful Exception
     * 
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private void parseRacadmTableOutputSpecialCaseForPowerSupplies(String racadmOutput) throws ParserConfigurationException, IOException {
        Scanner scanner = new Scanner(racadmOutput);

        Element entryElement = null;
        List<String> elementList = new ArrayList<String>();
        List<String> valueList = new ArrayList<String>();

        String section = "";
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine().toString();
            if (currentLine.isEmpty())
                continue;

            Scanner lineScanner = new Scanner(currentLine);

            // determine if it is the square bracket header section info
            if (lineScanner.findInLine("\\[") != null && lineScanner.findInLine("\\]") != null) {
                String delims = "[\\[\\]:]+";
                String[] tokens = currentLine.split(delims);
                String elem = null;
                for (String token : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(token)) {
                        elem = token.replace(" ", "_");
                        break;
                    }
                }
                section = elem;
                continue;
            }

            // not the square bracket header section
            if (lineScanner.findInLine("<") != null && lineScanner.findInLine(">") != null) {
                // If first line contains tags like <IO> etc, consider them
                // as elements of the xml document

                elementList.clear();
                String delims = "[<>\t#]+";
                String[] tokens = currentLine.split(delims);
                for (String str : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(str)) {
                        elementList.add(str.replace(" ", "_"));
                    }
                }
                continue;
                // System.out.println("");
            }

            // if we make it here, we have a row of data.
            // start our entry
            entryElement = dom.createElement("entry");

            currentLine = currentLine.replace("Failed(No Input Power)", "Failed  ");
            String regex = "\\([^)]*\\)";
            currentLine = currentLine.replaceAll(regex, "");
            // String delims = "[\t]+";
            String delims = ("\\s\\s+");
            // String delims = "[\s{2,}]+"
            Element childElement = null;
            valueList.clear();
            String[] tokens = currentLine.split(delims);
            for (String token : tokens) {
                valueList.add(token.trim());
            }

            while (valueList.size() < elementList.size())
                valueList.add(" ");

            if (valueList.size() != elementList.size()) {
                throw new ParserConfigurationException();
            }

            for (int i = 0; i < elementList.size(); i++) {
                String element = elementList.get(i);
                String value = valueList.get(i);

                // System.out.format("\nElement = %s\t\tValue = %s",
                // element, value);
                childElement = dom.createElement(element);
                childElement.setTextContent(value);
                entryElement.appendChild(childElement);
            }

            // add section info (if present) to the element and close the element
            if ((null != section) && !section.isEmpty()) {
                entryElement.setAttribute("section", section);
            }
            rootElement.appendChild(entryElement);

        }
        dom.appendChild(rootElement);
    }


    /**
     * Parse racadm output for commands whose output is formatted to screen with only spaces and NO tab characters. Example: getioinfo
     * 
     * @throws IOException
     */
    private void parseRacadmInfoOutput(String racadmOutput) throws IOException {
        Scanner scanner = new Scanner(racadmOutput);

        Element entryElement = null;
        List<String> elementList = new ArrayList<String>();
        List<Integer> indexNumbers = new ArrayList<Integer>();
        Map<String, String> tableDataForEveryRow = null;

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            Scanner lineScanner = new Scanner(currentLine);
            entryElement = dom.createElement("entry");
            if (lineScanner.findInLine("<") != null && lineScanner.findInLine(">") != null) {
                // If first line contains tags like <IO> etc, consider them
                // as elements of the xml document

                elementList.clear();
                indexNumbers.clear();
                String delims = "[<>#%]+";
                String[] tokens = currentLine.split(delims);
                for (String str : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(str)) {
                        elementList.add(str.replace(" ", "_"));

                        // find the index of the header token
                        // we need to include the delimnator when we get the indexof to prevent partial matches.
                        // e.g. the heading "Server_Slot" should match only "Server_Slot" and not match "Server_Slot_Name"
                        Pattern pattern = Pattern.compile("(\\[|\\<|\\#|\\%|\\+)" + str + "(\\]|\\>|\\#|\\%|\\+)");
                        Matcher matcher = pattern.matcher(currentLine);
                        while (matcher.find()) {
                            int index = matcher.start();
                            indexNumbers.add(index);
                        }

                        // System.out.format("\nElement = %s\tIndexNumber = %d",
                        // str, currentLine.indexOf(str) - 1);
                    }
                }
            } else if (null != lineScanner.findInLine(":")) {
                // this section populates a local list with list specific elements to add each row of the table.
                // example:
                // Sensor Type : Temperature
                // Sensor Units : Celsius
                String[] tokens = currentLine.split(":", 2);
                if ((null != tokens) && (2 == tokens.length)) {
                    // instantiate the list
                    if (null == tableDataForEveryRow) {
                        tableDataForEveryRow = new HashMap<String, String>();
                    }

                    // name - change first token to camel case with no space
                    String elementName = tokens[0].trim().replace(" ", "_").replace("/", "_");

                    // value
                    String value = tokens[1].trim();

                    // add the element to the list
                    tableDataForEveryRow.put(elementName, value);
                }
            } else {
                if (currentLine.isEmpty() || (currentLine.contains("[") && currentLine.contains("]"))) {
                    tableDataForEveryRow = null;
                    continue;
                }
                Element childElement = null;
                for (int i = 0; i < elementList.size(); i++) {
                    String element = elementList.get(i);
                    String value = null;
                    if (i < elementList.size() - 1) {
                        if (currentLine.length() >= indexNumbers.get(i + 1)) {
                            value = currentLine.substring(indexNumbers.get(i), indexNumbers.get(i + 1)).trim();
                        } else {
                            value = "N/A";
                        }
                    } else {
                        if (currentLine.length() >= indexNumbers.get(i)) {
                            value = currentLine.substring(indexNumbers.get(i)).trim();
                        } else {
                            value = "N/A";
                        }
                    }
                    // System.out.format("\nElement = %s\tValue = %s",
                    // elementList.get(i), value);
                    childElement = dom.createElement(element);
                    childElement.setTextContent(value);
                    entryElement.appendChild(childElement);
                }

                // set any table level elements into the row
                if ((null != tableDataForEveryRow) && (!tableDataForEveryRow.isEmpty())) {
                    Iterator iterator = tableDataForEveryRow.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> mapEntry = (Map.Entry<String, String>) iterator.next();
                        Element tableElement = dom.createElement(mapEntry.getKey());
                        tableElement.setTextContent(mapEntry.getValue());
                        entryElement.appendChild(tableElement);
                    }
                }

                // ad the row to the root element
                rootElement.appendChild(entryElement);
            }
        }
        dom.appendChild(rootElement);
    }


    /**
     * Parse racadm output for commands whose output is formatted to screen with '=' characters. Example: getsysinfo, getpminfo
     */
    private void parseRacadmDataOutput(String racadmOutput) {
        Scanner scanner = new Scanner(racadmOutput);

        Element entryElement = null;
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (RacadmResponseValidator.isStringEmpty(currentLine))
                continue;

            entryElement = dom.createElement("entry");
            if (!currentLine.contains("=")) {
                String delims = "[\\[\\]:]+";
                String[] tokens = currentLine.split(delims);
                String elem = null;
                for (String token : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(token)) {
                        elem = token.replace(" ", "_");
                        break;
                    }
                }
                entryElement.setAttribute("section", elem);
            } else {
                String[] tokens = currentLine.split("[=]+", 2);
                String elementName = tokens[0].trim().replace("# ", "").replace(" ", "_").replace("/", "_");
                String value = tokens[1].trim();
                Element childElement = dom.createElement(elementName);
                childElement.setTextContent(value);
                entryElement.appendChild(childElement);
            }
            rootElement.appendChild(entryElement);
        }
        dom.appendChild(rootElement);
    }


    /**
     * Parse racadm output for commands whose output is formatted to screen with '=' characters. Example: getsysinfo, getpminfo
     */
    private void parseRacadmDataOutputSingleEntry(String racadmOutput) {
        Scanner scanner = new Scanner(racadmOutput);

        Element entryElement = null;
        entryElement = dom.createElement("entry");
        boolean firstSection = true;
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (RacadmResponseValidator.isStringEmpty(currentLine))
                continue;

            // Create a new entry element in the dom
            // Some data output repeats the columns/data separated with "------" Like the following output.
            // getactiveerrors
            /*
             * Module ID = server-7 Severity = Critical Message = The watchdog timer expired. ------------------------------------------------------------------------------- Module
             * ID = chassis Severity = Critical Message = Fan 6 is removed. -------------------------------------------------------------------------------
             */
            if (currentLine.startsWith("--")) {
                // Close the previous entry element and create a new one.
                rootElement.appendChild(entryElement);
                if (scanner.hasNextLine()) {
                    // Make a new entry only if new data section is available.
                    entryElement = dom.createElement("entry");
                }
                continue;
            } else if (!currentLine.contains("=")) {
                // some raid commands return a success at the end. Ignore it.
                if (currentLine.equals("Success")) {
                    continue;
                }

                // if this is a new section, close out the previous section and start a new one
                if (!firstSection) {
                    rootElement.appendChild(entryElement);
                    entryElement = dom.createElement("entry");
                }
                firstSection = false;

                String delims = "[\\[\\]:]+";
                String[] tokens = currentLine.split(delims);
                String elem = null;
                String rawElem = null;
                for (String token : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(token)) {
                        rawElem = token;
                        elem = token.replace(" ", "_");
                        break;
                    }
                }
                entryElement.setAttribute("section", elem);

                // create a sub element with the name of the section
                // so we can match on it later
                if (null != rawElem) {
                    Element childElement = dom.createElement("section_identifier");
                    childElement.setTextContent(currentLine);
                    entryElement.appendChild(childElement);
                }

            } else {
                String[] tokens = currentLine.split("[=]+", 2);
                String elementName = tokens[0].trim().replace("# ", "").replace(" ", "_").replace("/", "_");
                String value = tokens[1].trim();
                Element childElement = dom.createElement(elementName);
                childElement.setTextContent(value);
                entryElement.appendChild(childElement);
            }
        }
        rootElement.appendChild(entryElement);
        dom.appendChild(rootElement);
    }


    /**
     * Parse racadm output for commands whose output is formatted to screen with '=' characters, only one entity is expected, and a final status messsage may be present.
     */
    private void parseRacadmDataOutputSingleEntryWithFinalStatusMessage(String racadmOutput) {
        Scanner scanner = new Scanner(racadmOutput);

        Element entryElement = null;
        entryElement = dom.createElement("entry");
        boolean firstSection = true;
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (RacadmResponseValidator.isStringEmpty(currentLine)) {
                continue;
            }

            if (!currentLine.contains("=")) {
                String delims = "[\\[\\]:]+";
                String[] tokens = currentLine.split(delims);
                String elem = null;
                String rawElem = null;
                for (String token : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(token)) {
                        rawElem = token;
                        elem = token.replace(" ", "_");
                        break;
                    }
                }

                if (firstSection) {
                    // write out a section header
                    entryElement.setAttribute("section", elem);

                    // create a sub element with the name of the section
                    // so we can match on it later
                    if (null != rawElem) {
                        Element childElement = dom.createElement("section_identifier");
                        childElement.setTextContent(currentLine);
                        entryElement.appendChild(childElement);
                    }
                    firstSection = false;
                } else {
                    // trailing status message
                    Element childElement = dom.createElement("Status_Message");
                    childElement.setTextContent(currentLine);
                    entryElement.appendChild(childElement);
                }

            } else {
                String[] tokens = currentLine.split("[=]+", 2);
                String elementName = tokens[0].trim().replace("# ", "").replace(" ", "_").replace("/", "_");
                String value = tokens[1].trim();
                Element childElement = dom.createElement(elementName);
                childElement.setTextContent(value);
                entryElement.appendChild(childElement);
            }
        }
        rootElement.appendChild(entryElement);
        dom.appendChild(rootElement);
    }


    /**
     * Parse racadm output for commands whose output is formatted to screen with '-' characters. Example: fanoffset
     */
    private void parseRacadmDataWithDashOutputSingleEntry(String racadmOutput) {
        Scanner scanner = new Scanner(racadmOutput);

        Element entryElement = null;
        entryElement = dom.createElement("entry");
        boolean firstSection = true;
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (RacadmResponseValidator.isStringEmpty(currentLine))
                continue;

            if (!currentLine.contains("-")) {

                // if this is a new section, close out the previous section and start a new one
                if (!firstSection) {
                    rootElement.appendChild(entryElement);
                    entryElement = dom.createElement("entry");
                }
                firstSection = false;

                String delims = "[\\[\\]:]+";
                String[] tokens = currentLine.split(delims);
                String elem = null;
                String rawElem = null;
                for (String token : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(token)) {
                        rawElem = token;
                        elem = token.replace(" ", "_");
                        break;
                    }
                }
                entryElement.setAttribute("section", elem);

                // create a sub element with the name of the section
                // so we can match on it later
                if (null != rawElem) {
                    Element childElement = dom.createElement("section_identifier");
                    childElement.setTextContent(currentLine);
                    entryElement.appendChild(childElement);
                }

            } else {
                String[] tokens = currentLine.split("[-]+", 2);
                String elementName = tokens[0].trim().replace("# ", "").replace(" ", "_").replace("/", "_");
                String value = tokens[1].trim();
                Element childElement = dom.createElement(elementName);
                childElement.setTextContent(value);
                entryElement.appendChild(childElement);
            }
        }
        rootElement.appendChild(entryElement);
        dom.appendChild(rootElement);
    }


    /**
     * Parse racadm output for commands whose output is formatted to screen with '=' characters. Example: getsysinfo, getpminfo
     */
    private void parseRacadmDataOutputWithoutEqualSign(String racadmOutput) {
        Scanner scanner = new Scanner(racadmOutput);

        Element entryElement = null;
        entryElement = dom.createElement("entry");
        boolean firstSection = true;
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (RacadmResponseValidator.isStringEmpty(currentLine))
                continue;
            currentLine = currentLine.replace("  ", "=");
            currentLine = currentLine.replace("==", "=");
            if (!currentLine.contains("=")) {
                if (!firstSection) {
                    rootElement.appendChild(entryElement);
                    entryElement = dom.createElement("entry");
                }
                firstSection = false;

                String delims = "[\\[\\]:]+";
                String[] tokens = currentLine.split(delims);
                String elem = null;
                for (String token : tokens) {
                    if (!RacadmResponseValidator.isStringEmpty(token)) {
                        elem = token.replace(" ", "_");
                        break;
                    }
                }
                entryElement.setAttribute("section", elem);
            } else {
                String[] tokens = currentLine.split("[=]+", 2);
                String elementName = tokens[0].trim().replace("# ", "").replace(" ", "_").replace("/", "_");
                String value = tokens[1].trim();
                Element childElement = dom.createElement(elementName);
                childElement.setTextContent(value);
                entryElement.appendChild(childElement);
            }
            rootElement.appendChild(entryElement);
        }
        rootElement.appendChild(entryElement);
        dom.appendChild(rootElement);
    }


    private void parseRacadmLineOutput(String racadmOutput) {
        Scanner scanner = new Scanner(racadmOutput);

        Element entryElement = null;
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (RacadmResponseValidator.isStringEmpty(currentLine))
                continue;
            racadmOutput = StringUtils.trim(racadmOutput);
            entryElement = dom.createElement("entry");
            entryElement.setAttribute("section", racadmOutput);
            rootElement.appendChild(entryElement);
        }
        dom.appendChild(rootElement);
    }


    /**
     * Parse racadm output for commands whose output is formatted to screen with '=' characters. Example: getsysinfo, getpminfo
     */
    private void parseLicenseSpecificOutput(String racadmOutput) {
        Scanner scanner = new Scanner(racadmOutput);
        boolean firstEntryElement = true;

        Element entryElement = null;
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (RacadmResponseValidator.isStringEmpty(currentLine)) {
                continue;
            }

            if (currentLine.contains("#")) {
                // recursive call to process
                // NOTE: ProdessLineDetails calls nextline.
                boolean hasMoreDetails = true;
                ;
                while (hasMoreDetails) {
                    currentLine = processLicenseDetails(currentLine, scanner, entryElement);
                    if (null == currentLine || !currentLine.contains("#")) {
                        hasMoreDetails = false;
                    }
                }
                ;

                if (RacadmResponseValidator.isStringEmpty(currentLine)) {
                    continue;
                }
            }

            if (!currentLine.contains("=")) {
                // this is an entry element

                // see if we should close our element
                if (!firstEntryElement) {
                    rootElement.appendChild(entryElement);
                }
                firstEntryElement = false;

                // create a new entry element and set the name child element
                entryElement = dom.createElement("entry");
                Element childElement = dom.createElement("Device_Name");
                childElement.setTextContent(currentLine);
                entryElement.appendChild(childElement);

            } else {
                if (null == entryElement) {
                    entryElement = dom.createElement("entry");
                }
                String[] tokens = currentLine.split("[=]+", 2);
                String elementName = tokens[0].trim().replace("# ", "").replace(" ", "_").replace("/", "_");
                String value = tokens[1].trim();
                Element childElement = dom.createElement(elementName);
                childElement.setTextContent(value);
                entryElement.appendChild(childElement);
            }
        }
        rootElement.appendChild(entryElement);
        dom.appendChild(rootElement);
    }


    private String processLicenseDetails(String currentLine, Scanner scanner, Element entryElement) {
        // create a new LicenseViewDetails element
        Element licenseDetailsElement = dom.createElement("LicenseViewDetails");

        // set the element name (i.e. <name>License #1</name> )
        Element licenseDetailsNameChildElement = dom.createElement("Name");
        licenseDetailsNameChildElement.setTextContent(currentLine.trim());

        // add the name element to the license details element
        licenseDetailsElement.appendChild(licenseDetailsNameChildElement);

        // go thru the remaining elements and add them to the license detail elemment
        String line = null;
        while (scanner.hasNextLine()) {
            // read the new line
            line = scanner.nextLine();

            // if we are at the start of another section, bail out
            // calling code will call the method recursively
            if (line.contains("=")) {
                // processing the details rows
                String[] tokens = line.split("[=]+", 2);
                String elementName = tokens[0].trim().replace("# ", "").replace(" ", "_").replace("/", "_");
                String value = tokens[1].trim();
                Element childElement = dom.createElement(elementName);
                childElement.setTextContent(value);
                licenseDetailsElement.appendChild(childElement);

            } else {
                break;
            }
        }
        entryElement.appendChild(licenseDetailsElement);
        return line;
    }

    // --------------------------------------------------------------------

    private static Document racadmDoc = null;


    private void initializeRacadmXML() throws ParserConfigurationException, SAXException, IOException {
        if (racadmDoc == null) {
            InputStream is = null;
            try {
                if ((null != this.getClass()) && (null != this.getClass().getClassLoader())) {
                    is = this.getClass().getClassLoader().getResourceAsStream("commands/racadmCommands.xml"); // from jar file

                    if (is == null) {
                        is = this.getClass().getClassLoader().getResourceAsStream("wsdl/wsman/racadmCommands.xml"); // from junit
                    }

                    /*
                     * DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance(); domFactory.setNamespaceAware(true); // DO NOT remove this line! DocumentBuilder
                     * builder = domFactory.newDocumentBuilder(); if(null != builder){ racadmDoc = builder.parse(is); }
                     */
                    racadmDoc = XmlHelper.convertInputStreamToXmlDocument(is, true);
                }
            } finally {
                try {
                    if (is != null)
                        is.close();
                } catch (IOException e) {
                }
            }
        }
    }


    /**
     * 
     * @param command
     * @return Output type of a given command
     */
    private String getOutputType(String command) {
        String outputType = null;

        try {
            if (racadmDoc == null) {
                initializeRacadmXML(); // initialize the racadmDoc. This is a xml representing the commands supported.
            }

            // Some commands have different output type for the switch e.g. getpciecfg -c command
            if (command.length() > 0) {
                // first try to match on the literal command
                outputType = evaluateCommand(command);
                if (null != outputType) {
                    return outputType;
                }

                // split up the command to parts
                String[] s = command.split(" ");

                // start taking away parts of the command and see if we have a match
                for (int max = (s.length - 1); max >= 1; max--) {
                    StringBuilder newCommandBuilder = new StringBuilder();
                    for (int i = 0; i < max; i++) {
                        if (i > 0) {
                            newCommandBuilder.append(" ");
                        }
                        newCommandBuilder = newCommandBuilder.append(s[i]);
                    }
                    outputType = evaluateCommand(newCommandBuilder.toString());
                    if (null != outputType) {
                        return outputType;
                    }
                }
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return outputType;
    }


    private String evaluateCommand(String command) {
        String outputType = null;
        try {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();
            XPathExpression expr = null;
            // Note: using the string command to prevent any dash from being treated as a minus sign for subtraction
            command = String.format("//*[command=string('%s')]", command);
            expr = xpath.compile(command);
            Object result = expr.evaluate(racadmDoc, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            if (nodes.getLength() > 0) {
                outputType = nodes.item(0).getNodeName();
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return outputType;
    }


    public Document parseRacadmTextOutput(String command, String racadmOutput) throws ParserConfigurationException, IOException {
        RacadmXMLParser xmlParser = new RacadmXMLParser();

        String outputType = getOutputType(command);
        if (null == outputType) {
            ExceptionUtilities.handleRuntimeCoreException(200504);
        }

        // perform pre-xml processing if the metadata command is present
        racadmOutput = applyMetadataCommands(racadmOutput, command);

        if (outputType.equalsIgnoreCase("data"))
            xmlParser.parseRacadmDataOutput(racadmOutput);
        else if (outputType.equalsIgnoreCase("dataintosingleentry"))
            xmlParser.parseRacadmDataOutputSingleEntry(racadmOutput);
        else if (outputType.equalsIgnoreCase("dataintosingleentrywithfinalstatusmessage"))
            xmlParser.parseRacadmDataOutputSingleEntryWithFinalStatusMessage(racadmOutput);
        else if (outputType.equalsIgnoreCase("datawithdashintosingleentry"))
            xmlParser.parseRacadmDataWithDashOutputSingleEntry(racadmOutput);
        else if (outputType.equalsIgnoreCase("info"))
            xmlParser.parseRacadmInfoOutput(racadmOutput);
        else if (outputType.equalsIgnoreCase("table"))
            xmlParser.parseRacadmTableOutput(racadmOutput);
        else if (outputType.equalsIgnoreCase("tablespecialcaseforpowersupplies"))
            xmlParser.parseRacadmTableOutputSpecialCaseForPowerSupplies(racadmOutput);
        else if (outputType.equalsIgnoreCase("line"))
            xmlParser.parseRacadmLineOutput(racadmOutput);
        else if (outputType.equalsIgnoreCase("datanoequalsign"))
            xmlParser.parseRacadmDataOutputWithoutEqualSign(racadmOutput);
        else if (outputType.equalsIgnoreCase("licensespecific"))
            xmlParser.parseLicenseSpecificOutput(racadmOutput);
        else
            ExceptionUtilities.handleRuntimeCoreException(200504);
        return xmlParser.dom;
    }


    public static final String convertRacadmXMLOutputToText(String xmlOutput) throws ParserConfigurationException, SAXException, IOException {
        String result = null;
        /*
         * InputSource inputSource = new InputSource(); inputSource.setCharacterStream(new StringReader(xmlOutput)); DocumentBuilderFactory dbf =
         * DocumentBuilderFactory.newInstance(); DocumentBuilder docBuilder = dbf.newDocumentBuilder(); Document doc = docBuilder.parse(inputSource);
         */
        Document doc = XmlHelper.convertStringToXMLDocument(xmlOutput);
        Element rootElement = doc.getDocumentElement();
        NodeList nodeList = rootElement.getElementsByTagName("CMDOUTPUT");
        if (nodeList != null && nodeList.getLength() > 0) {
            Element elemContents = (Element) nodeList.item(0);
            result = elemContents.getTextContent().toString();
        }
        return result;
    }


    /**
     * This method is used in conjunction with a spectre -metadata command appended to the racadm command. The -metadata command and everything after it is stripped off in the
     * RacadmCommandHelper.java before the racadm command is executed.
     * 
     * With this method, commands after the -metadata tag are parsed and executed. Currently two commands are supported: includeallbefore=text_to_match Cuts off all text after the
     * matched text excludeallafter=text_to_match Cuts off all text before the matched text. Spaces are not allowed in the text to match. Replace spaces with an underscore.
     * 
     * @param racadmOutput
     * @param command
     * @return
     */
    private String applyMetadataCommands(String racadmOutput, String command) {
        // determine any instructions from the metadata
        if ((null != command) && !command.isEmpty()) {
            // get the index for the metadata
            String metadatastring = "-metadata=";
            int metadataIndex = command.indexOf(metadatastring);

            // get the metadata instructions using substring and apply the instructions
            String metadatainstructions = command.substring(metadataIndex + metadatastring.length());
            String[] instructions = null;
            if (!metadatainstructions.isEmpty()) {
                if (metadatainstructions.contains("|")) {
                    instructions = metadatainstructions.split("|");
                } else {
                    instructions = new String[] { metadatainstructions };
                }
                if ((null != instructions) && (instructions.length > 0)) {
                    String includeBefore = "includeallbefore=";
                    String excludeBefore = "excludeallbefore=";
                    for (String instruction : instructions) {

                        // include all before
                        if (instruction.toLowerCase().startsWith(includeBefore)) {
                            String stringToMatch = instruction.replace(includeBefore, "");
                            stringToMatch = stringToMatch.replace("_", " ");
                            int index = racadmOutput.indexOf(stringToMatch);
                            if (index > 1) {
                                racadmOutput = racadmOutput.substring(0, index);
                            }

                        }

                        // exclude all before
                        if (instruction.toLowerCase().startsWith(excludeBefore)) {
                            String stringToMatch = instruction.replace(excludeBefore, "");
                            stringToMatch = stringToMatch.replace("_", " ");
                            int index = racadmOutput.indexOf(stringToMatch);
                            if (index > 1) {
                                racadmOutput = racadmOutput.substring(index - 1, racadmOutput.length());
                            }
                        }
                    }
                }
            }

        }
        return racadmOutput;

    }
}
