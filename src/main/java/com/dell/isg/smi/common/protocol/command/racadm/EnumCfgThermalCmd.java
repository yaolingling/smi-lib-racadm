/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.dell.isg.smi.common.protocol.command.chassis.entity.EnhancedCoolingMode;

public class EnumCfgThermalCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumCfgThermalCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumCfgThermalCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("getconfig -g cfgThermal", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumCfgThermalCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumCfgThermalCmd()");
    }


    /**
     * Gets the entity
     * 
     * @return FanOffset
     * @throws Exception
     */
    public EnhancedCoolingMode getEnhancedCoolingMode() throws Exception {
        EnhancedCoolingMode enhancedCoolingMode = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                enhancedCoolingMode = xmlToEntity(EnhancedCoolingMode.class, firstNode.getFirstChild());
            }
        }
        return enhancedCoolingMode;
    }

}
