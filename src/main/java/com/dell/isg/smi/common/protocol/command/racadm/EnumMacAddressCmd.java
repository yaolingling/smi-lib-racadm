/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.dell.isg.smi.common.protocol.command.chassis.entity.MacAddress;

public class EnumMacAddressCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumMacAddressCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumMacAddressCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumMacAddressCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumMacAddressCmd()");
    }


    /**
     * Gets the version object for the CMC
     * 
     * @param cmcName name of the cmc
     * @return CmcVersion
     * @throws Exception
     */
    public MacAddress getMacAddressByModule(String moduleName) throws Exception {
        this.setCommand(String.format("getmacaddress -m %s", moduleName));
        MacAddress macAddress = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                macAddress = xmlToEntity(MacAddress.class, firstNode.getFirstChild());
            }
        }
        return macAddress;
    }

}
