/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.ChassisNicCfg;

public class EnumChassisRedundancyModeCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumChassisRedundancyModeCmd.class);
    private String model;
    private String ipAddress;
    private String userName;
    private String password;
    private boolean bCertCheck;


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumChassisRedundancyModeCmd(String ipAddr, String userName, String passwd, boolean bCertCheck, String model) {
        super("getredundancymode", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumChassisRedundancyModeCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        this.model = model;
        this.ipAddress = ipAddr;
        this.userName = userName;
        this.password = passwd;
        this.bCertCheck = bCertCheck;
        logger.trace("Exiting constructor: EnumChassisRedundancyModeCmd()");
    }


    public String getRedundancyMode() throws Exception {
        String mode = "";
        if (model.toLowerCase().equals("poweredge m1000e") || model.toLowerCase().equals("poweredge vrtx")) {
            mode = this.getResult();
            if (mode != null && mode.contains("Non-Redundant")) {
                mode = "Non-Redundant";
            } else if (mode != null && mode.contains("Redundant")) {
                mode = "Redundant";
            } else {
                mode = "N/A";
            }
        } else {
            EnumNicCfgCmd cmd = new EnumNicCfgCmd(ipAddress, userName, password, bCertCheck);
            ChassisNicCfg cfg = cmd.getNicCfgForChassis();
            if (cfg != null) {
                if (cfg.getRedundantMode() != null) {
                    if (cfg.getRedundantMode().equals("1")) {
                        mode = "Redundant";
                    } else {
                        mode = "Non-Redundant";
                    }
                }
            }
        }

        return mode;
    }

}
