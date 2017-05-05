/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumArrayCfgCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumArrayCfgCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumArrayCfgCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumArrayCfgCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumArrayCfgCmd()");
    }


    /**
     * Gets the array config for a module
     * 
     * @param moduleName - the server module, server-x
     * @return EquallogicArrayConfig
     * @throws Exception
     */
    public Object getArrayCfg(String moduleName) throws Exception {
        this.setCommand(String.format("getarraycfg -m %s", moduleName));
        /*
         * EquallogicArrayConfig equallogicArrayConfig = null; if(null != this.getDocument()){ Node firstNode = this.getDocument().getFirstChild(); if( (null != firstNode) &&
         * firstNode.hasChildNodes() ){ equallogicArrayConfig = xmlToEntity(EquallogicArrayConfig.class, firstNode.getFirstChild()); } } return equallogicArrayConfig;
         */
        return null;
    }
}
