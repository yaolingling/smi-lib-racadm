/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.CfgTraps;

public class SetCfgTrapsCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(SetCfgTrapsCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public SetCfgTrapsCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: SetCfgTrapsCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: SetCfgTrapsCmd()");
    }


    public boolean setTrapCfg(int index, CfgTraps cfg) throws Exception {
        // First set the property cfgTrapsEnable
        // Then set the property cfgTrapsAlertDestIPAddr
        // Then set the property cfgTrapsCommunityName
        // 0 = disabled
        // 1 = enabled.
        if (cfg != null) {
            try {

                this.setCommand(String.format("config -g cfgAlerting -o cfgAlertingEnable %s", "1"));
                String result = this.getResult();
                if (result == null || !result.contains("Object value modified successfully")) {
                    return false;
                }

                this.setCommand(String.format("config -g cfgTraps -i %s -o cfgTrapsEnable %s", index, cfg.getCfgTrapsEnable()));
                result = this.getResult();
                if (result == null || !result.contains("Object value modified successfully")) {
                    return false;
                }

                this.setCommand(String.format("config -g cfgTraps -i %s -o cfgTrapsAlertDestIPAddr %s", index, cfg.getCfgTrapsAlertDestIPAddr()));
                result = this.getResult();
                if (result == null || !result.contains("Object value modified successfully")) {
                    return false;
                }

                this.setCommand(String.format("config -g cfgTraps -i %s -o cfgTrapsCommunityName %s", index, cfg.getCfgTrapsCommunityName()));
                result = this.getResult();
                if (result == null || !result.contains("Object value modified successfully")) {
                    return false;
                }

                return true;
            } catch (Exception e) {
                logger.error("Failed to set the trap config");
                logger.error(e.getMessage());
            }
        }
        return false;
    }

}
