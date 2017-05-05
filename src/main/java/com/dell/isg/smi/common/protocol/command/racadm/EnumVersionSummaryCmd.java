/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.CMCVersionSummary;
import com.dell.isg.smi.common.protocol.command.chassis.entity.MainBoardVersionSummary;
import com.dell.isg.smi.common.protocol.command.chassis.entity.ServerVersionSummary;
import com.dell.isg.smi.common.protocol.command.chassis.entity.SwitchVersionSummary;

public class EnumVersionSummaryCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumVersionSummaryCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumVersionSummaryCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("getversion", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumVersionSummaryCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumVersionSummaryCmd()");
    }


    public List<ServerVersionSummary> getAllServerVersionSummarys() throws Exception {
        String xpathExpressionString = String.format("//root/entry[starts-with(Server,'server-')]");
        List<ServerVersionSummary> serverVersionSummaryList = filterUsingXpathForNodeList(ServerVersionSummary.class, xpathExpressionString);
        return serverVersionSummaryList;
    }


    public List<ServerVersionSummary> getAllServerVersionEquallogicSummarys() throws Exception {
        String xpathExpressionString = String.format("//root/entry[starts-with(Server,'server-') and starts-with(Blade_Type,'PS-M')]");
        List<ServerVersionSummary> serverVersionSummaryList = filterUsingXpathForNodeList(ServerVersionSummary.class, xpathExpressionString);
        return serverVersionSummaryList;
    }


    public List<SwitchVersionSummary> getAllSwitchVersionSummarys() throws Exception {
        String xpathExpressionString = String.format("//root/entry[starts-with(Switch,'switch-')]");
        List<SwitchVersionSummary> switchVersionSummaryList = filterUsingXpathForNodeList(SwitchVersionSummary.class, xpathExpressionString);
        return switchVersionSummaryList;
    }


    public List<CMCVersionSummary> getAllCMCVersionSummarys() throws Exception {
        String xpathExpressionString = String.format("//root/entry[starts-with(CMC,'cmc-')]");
        List<CMCVersionSummary> cmcVersionSummaryList = filterUsingXpathForNodeList(CMCVersionSummary.class, xpathExpressionString);
        return cmcVersionSummaryList;
    }


    public List<MainBoardVersionSummary> getAllMainBoardSummarys() throws Exception {
        String xpathExpressionString = String.format("//root/entry[starts-with(Chassis_Infrastructure,'Main Board')]");
        List<MainBoardVersionSummary> cmcVersionSummaryList = filterUsingXpathForNodeList(MainBoardVersionSummary.class, xpathExpressionString);
        return cmcVersionSummaryList;
    }

}
