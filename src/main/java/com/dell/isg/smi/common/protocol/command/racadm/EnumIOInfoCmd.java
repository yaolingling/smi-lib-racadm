/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.isg.smi.common.protocol.command.chassis.entity.IoInfo;

public class EnumIOInfoCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumIOInfoCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumIOInfoCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("getioinfo", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumRacadmIoInfoCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumRacadmIoInfoCmd()");
    }


    /**
     * Gets all of the entities
     * 
     * @return List of IoInfo
     * @throws Exception
     */
    public List<IoInfo> getAllRacadmIoInfo() throws Exception {
        List<IoInfo> ioInfoList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                ioInfoList = xmlToEntity(IoInfo.class, nodeList);
            }
        }
        return ioInfoList;
    }
}
