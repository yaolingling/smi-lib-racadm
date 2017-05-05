/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.isg.smi.common.protocol.command.chassis.entity.ChassisActiveError;

public class EnumChassisActiveErrorsCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumChassisActiveErrorsCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumChassisActiveErrorsCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumChassisActiveErrorsCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumChassisActiveErrorsCmd()");
    }


    /**
     * Gets all of the errors for the chassis and its components
     * 
     * @return List<ChassisActiveError>
     * @throws Exception
     */
    public List<ChassisActiveError> getActiveErrors() throws Exception {
        this.setCommand("getactiveerrors");
        List<ChassisActiveError> errors = new ArrayList<ChassisActiveError>();
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                errors = xmlToEntity(ChassisActiveError.class, nodeList);
            }
        }
        return errors;
    }


    /**
     * Gets all of the errors for a specific module
     * 
     * @return List<ChassisActiveError>
     * @throws Exception
     */
    public List<ChassisActiveError> getActiveErrorsForModule(String moduleName) throws Exception {
        this.setCommand(String.format("getactiveerrors -m %s", moduleName));
        List<ChassisActiveError> errors = new ArrayList<ChassisActiveError>();
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                errors = xmlToEntity(ChassisActiveError.class, nodeList);
            }
        }
        return errors;
    }

}
