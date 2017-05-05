/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.isg.smi.common.protocol.command.chassis.entity.IoInfoStack;

public class EnumIOInfoStackCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumIOInfoStackCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumIOInfoStackCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("getioinfo -s", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumRacadmIoInfoCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumRacadmIoInfoCmd()");
    }


    /**
     * Gets all of the entities
     * 
     * @return List of RacadIoInfoEntity
     * @throws Exception
     */
    public List<IoInfoStack> getAllIoInfoStack() throws Exception {
        List<IoInfoStack> ioInfoStackList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                ioInfoStackList = xmlToEntity(IoInfoStack.class, nodeList);
            }
        }
        return ioInfoStackList;
    }
}
