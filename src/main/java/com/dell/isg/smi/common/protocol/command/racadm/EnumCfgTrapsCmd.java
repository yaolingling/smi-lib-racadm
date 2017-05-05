/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.dell.isg.smi.common.protocol.command.chassis.entity.CfgTraps;

public class EnumCfgTrapsCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumCfgTrapsCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumCfgTrapsCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumTrapsCfgCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumTrapsCfgCmd()");
    }


    public CfgTraps getTrapCfgByIndex(int index) throws Exception {
        this.setCommand(String.format("getconfig -g cfgTraps -i %s", index));
        CfgTraps cfg = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                cfg = xmlToEntity(CfgTraps.class, firstNode.getFirstChild());
            }
        }
        return cfg;
    }


    public List<CfgTraps> getAllTrapCfg() {
        List<CfgTraps> trapCfgs = new ArrayList<CfgTraps>();
        try {
            for (int i = 1; i <= 4; i++) {
                CfgTraps cfg = getTrapCfgByIndex(i);
                if (cfg != null) {
                    trapCfgs.add(cfg);
                }
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return trapCfgs;
    }

}
