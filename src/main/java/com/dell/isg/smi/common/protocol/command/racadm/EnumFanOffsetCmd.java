/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.dell.isg.smi.common.protocol.command.chassis.entity.FanOffset;

public class EnumFanOffsetCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumFanOffsetCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumFanOffsetCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("fanoffset", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumFanOffsetCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumFanOffsetCmd()");
    }


    /**
     * Gets all of the entities
     * 
     * @return FanOffset
     * @throws Exception
     */
    public FanOffset getFanOffset() throws Exception {
        FanOffset fanOffset = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                fanOffset = xmlToEntity(FanOffset.class, firstNode.getFirstChild());
            }
        }
        return fanOffset;
    }

}
