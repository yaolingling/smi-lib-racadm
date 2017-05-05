/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.dell.isg.smi.common.protocol.command.chassis.entity.CfgKvmInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.KvmInfo;

public class EnumKvmInfoCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumKvmInfoCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumKvmInfoCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("getkvminfo", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumKvmInfoCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumKvmInfoCmd()");
    }


    /**
     * Gets all of the entities
     * 
     * @return KvmModule
     * @throws Exception
     */
    public KvmInfo getKvmInfo() throws Exception {
        this.setCommand("getkvminfo");
        KvmInfo kvmInfo = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                kvmInfo = xmlToEntity(KvmInfo.class, firstNode.getFirstChild());
            }
        }
        return kvmInfo;
    }


    /**
     * Gets all of the entities
     * 
     * @return KvmModule
     * @throws Exception
     */
    public CfgKvmInfo getCfgKvmInfo() throws Exception {
        this.setCommand("getconfig -g cfgkvminfo");
        CfgKvmInfo cfgKvmInfo = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                cfgKvmInfo = xmlToEntity(CfgKvmInfo.class, firstNode.getFirstChild());
            }
        }
        return cfgKvmInfo;
    }
}
