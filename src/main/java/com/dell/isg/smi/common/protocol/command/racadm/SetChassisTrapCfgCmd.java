/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.CfgTraps;
import com.dell.isg.smi.common.protocol.command.cmc.entity.RacadmCredentials;

public class SetChassisTrapCfgCmd {

    private static final Logger logger = LoggerFactory.getLogger(SetChassisTrapCfgCmd.class);
    private RacadmCredentials credentials = null;
    private String ipAddr = null;
    private CfgTraps trapCfg = null;


    public SetChassisTrapCfgCmd(String ipAddr, String userName, String passwd, boolean check, CfgTraps cfg) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: SetChassisTrapCfgCmd(String ipAddr - %s, String userName - %s, String passwd - %s)", ipAddr, userName, "####"));
        }

        this.credentials = new RacadmCredentials();
        this.credentials.setUserName(userName);
        this.credentials.setPassword(passwd);
        this.credentials.setCertificateCheck(check);
        this.ipAddr = ipAddr;
        this.trapCfg = cfg;

        logger.trace("Exiting constructor: SetChassisTrapCfgCmd()");
    }


    public boolean execute() throws Exception {
        List<CfgTraps> trapCfgs = null;
        EnumCfgTrapsCmd cmd = new EnumCfgTrapsCmd(ipAddr, credentials.getUserName(), credentials.getPassword(), credentials.isCertificateCheck());
        trapCfgs = cmd.getAllTrapCfg();
        int indexToSet = 0;
        if (trapCfgs != null) {
            int emptyIndex = 0;
            int existingDestIndex = 0;
            int disabledIndex = 0;
            for (int i = 0; i < trapCfgs.size(); i++) {
                CfgTraps cfg = trapCfgs.get(i);
                // Try to find an empty place.
                if (emptyIndex == 0 && (cfg.getCfgTrapsAlertDestIPAddr() == null || cfg.getCfgTrapsAlertDestIPAddr().isEmpty())) {
                    emptyIndex = i + 1;
                }
                // Try to find the place for the same ip address.
                if (cfg.getCfgTrapsAlertDestIPAddr() != null && cfg.getCfgTrapsAlertDestIPAddr().equals(trapCfg.getCfgTrapsAlertDestIPAddr())) {
                    existingDestIndex = i + 1;
                    break;
                }
                // Try to find a place where traps are disabled.
                if (disabledIndex == 0 && cfg.getCfgTrapsEnable() != null && cfg.getCfgTrapsEnable().trim().equals("0")) {
                    disabledIndex = i + 1;
                }
            }
            /**
             * Control Flow: 1. Check if we get a slot with the given ip address. Update that. 2. If Step 1 can't be done then look for an empty slot. Set that. 3. If no empty slot
             * is found then set a disabled one.
             */
            if (existingDestIndex > 0) {
                indexToSet = existingDestIndex;
            } else if (emptyIndex > 0) {
                indexToSet = emptyIndex;
            } else if (disabledIndex > 0) {
                indexToSet = disabledIndex;
            }
            // Set the config if some valid slot is found.
            if (indexToSet > 0) {
                SetCfgTrapsCmd setCmd = new SetCfgTrapsCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
                return setCmd.setTrapCfg(indexToSet, this.trapCfg);
            } else {
                logger.error("Chassis TRAP Config- NO free SLOT available");
            }

        }
        return false;
    }
}
