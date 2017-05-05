/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.dell.isg.smi.common.protocol.command.chassis.entity.ChassisNicCfg;
import com.dell.isg.smi.common.protocol.command.chassis.entity.ServerNicCfg;
import com.dell.isg.smi.common.protocol.command.chassis.entity.SwitchNicCfg;

public class EnumNicCfgCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumNicCfgCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumNicCfgCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumNicCfgCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumNicCfgCmd()");
    }


    public SwitchNicCfg getNicCfgForSwitch(String switchName) throws Exception {
        SwitchNicCfg switchNicCfg = null;

        this.setCommand(String.format("getniccfg -m %s", switchName.toLowerCase()));
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                switchNicCfg = xmlToEntity(SwitchNicCfg.class, firstNode.getFirstChild());
            }
        }
        return switchNicCfg;
    }


    public ServerNicCfg getNicCfgForServer(String serverName) throws Exception {
        this.setCommand(String.format("getniccfg -m %s", serverName));
        ServerNicCfg serverNicCfg = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                serverNicCfg = xmlToEntity(ServerNicCfg.class, firstNode.getFirstChild());
            }
        }
        return serverNicCfg;
    }


    public ChassisNicCfg getNicCfgForChassis() throws Exception {
        this.setCommand(String.format("getniccfg"));
        ChassisNicCfg chassisNicCfg = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                chassisNicCfg = xmlToEntity(ChassisNicCfg.class, firstNode.getFirstChild());
            }
        }
        return chassisNicCfg;
    }
}
