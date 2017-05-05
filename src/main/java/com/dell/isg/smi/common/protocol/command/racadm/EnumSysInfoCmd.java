/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.CMCIPv4Info;
import com.dell.isg.smi.common.protocol.command.chassis.entity.CMCIPv6Info;
import com.dell.isg.smi.common.protocol.command.chassis.entity.CMCInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.CMCNetworkInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.ChassisInfo;

public class EnumSysInfoCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumSysInfoCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumSysInfoCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("getsysinfo", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumSysInfoCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumSysInfoCmd()");
    }


    public CMCInfo getCMCInfoEntity() throws Exception {
        String xpathExpressionString = String.format("//root/entry[@section=\"CMC_Information\"]");
        CMCInfo cmcInfo = filterUsingXpathforNode(CMCInfo.class, xpathExpressionString);
        return cmcInfo;
    }


    public CMCNetworkInfo getCMCNetworkInfoEntity() throws Exception {
        String xpathExpressionString = String.format("//root/entry[@section=\"CMC_Network_Information\"]");
        CMCNetworkInfo cmcNetworkInfo = filterUsingXpathforNode(CMCNetworkInfo.class, xpathExpressionString);
        return cmcNetworkInfo;
    }


    public CMCIPv4Info getCMCIPv4InfoEntity() throws Exception {
        String xpathExpressionString = String.format("//root/entry[@section=\"CMC_IPv4_Information\"]");
        CMCIPv4Info cmcIPv4Info = filterUsingXpathforNode(CMCIPv4Info.class, xpathExpressionString);
        return cmcIPv4Info;
    }


    public CMCIPv6Info getCMCIPv6InfoEntity() throws Exception {
        String xpathExpressionString = String.format("//root/entry[@section=\"CMC_IPv6_Information\"]");
        CMCIPv6Info cmcIPv6Info = filterUsingXpathforNode(CMCIPv6Info.class, xpathExpressionString);
        return cmcIPv6Info;
    }


    public ChassisInfo getChassisInfoEntity() throws Exception {
        String xpathExpressionString = String.format("//root/entry[@section=\"Chassis_Information\"]");
        ChassisInfo chassisInfo = filterUsingXpathforNode(ChassisInfo.class, xpathExpressionString);
        return chassisInfo;
    }

}
