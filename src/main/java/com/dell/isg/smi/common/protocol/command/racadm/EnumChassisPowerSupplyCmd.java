/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.ChassisPowerSupplyStatus;
import com.dell.isg.smi.common.protocol.command.chassis.entity.ModInfo;
import com.dell.isg.smi.common.protocol.command.cmc.entity.ChassisPowerSupplyEntity;
import com.dell.isg.smi.common.protocol.command.cmc.entity.RacadmCredentials;
import com.dell.isg.smi.common.protocol.command.racadm.EnumModInfoCmd.ModInfoTypeEnum;

public class EnumChassisPowerSupplyCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumChassisPowerSupplyCmd.class);
    private RacadmCredentials credentials = null;
    private String ipAddr = null;


    public EnumChassisPowerSupplyCmd(String ipAddr, String userName, String passwd, boolean check) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumChassisCMCViewCmd(String ipAddr - %s, String userName - %s, String passwd - %s)", ipAddr, userName, "####"));
        }

        this.credentials = new RacadmCredentials();
        this.credentials.setUserName(userName);
        this.credentials.setPassword(passwd);
        this.credentials.setCertificateCheck(check);
        this.ipAddr = ipAddr;

        logger.trace("Exiting constructor: EnumChassisCMCViewCmd()");
    }


    private List<ChassisPowerSupplyEntity> parseModuleInfoInfoResponse(List<ModInfo> modInfoList) {
        List<ChassisPowerSupplyEntity> powerSupplies = new LinkedList<ChassisPowerSupplyEntity>();
        if ((null != modInfoList) && !modInfoList.isEmpty()) {
            for (ModInfo modInfo : modInfoList) {
                ChassisPowerSupplyEntity powerSupply = new ChassisPowerSupplyEntity();
                powerSupply.setName(modInfo.getModule());
                powerSupply.setPowerState(modInfo.getPwrState());
                powerSupply.setHealth(modInfo.getHealth());
                powerSupply.setPresent(modInfo.getPresence());
                powerSupplies.add(powerSupply);
            }
        }
        return powerSupplies;
    }


    public List<ChassisPowerSupplyEntity> execute() throws Exception {
        EnumModInfoCmd enumModInfoCmd = new EnumModInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        return execute(enumModInfoCmd);
    }


    public List<ChassisPowerSupplyEntity> execute(EnumModInfoCmd enumModInfoCmd) throws Exception {
        // Send the get module info command to load all the modules.
        List<ModInfo> modInfoList = enumModInfoCmd.getModInfoEntitiesByType(ModInfoTypeEnum.PS);
        List<ChassisPowerSupplyEntity> powerSupplies = this.parseModuleInfoInfoResponse(modInfoList);
        List<ChassisPowerSupplyStatus> chassisPowerSupplyStatusList = null;
        try {
            EnumPbInfoCmd enumPbInfoCmd = new EnumPbInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
            chassisPowerSupplyStatusList = enumPbInfoCmd.getChassisPowerSupplyStatus();
        } catch (Exception e) {
            logger.warn("Failed to load the power budget information");
            logger.warn(e.getMessage());
        }

        // Set the power budget information.
        if ((null != powerSupplies) && !powerSupplies.isEmpty()) {
            for (ChassisPowerSupplyEntity ps : powerSupplies) {
                String moduleName = ps.getName().replace("-", "");
                if ((null != chassisPowerSupplyStatusList) && !chassisPowerSupplyStatusList.isEmpty()) {
                    for (ChassisPowerSupplyStatus chassisPowerSupplyStatus : chassisPowerSupplyStatusList) {
                        if (moduleName.equalsIgnoreCase(chassisPowerSupplyStatus.getName())) {
                            ps.setInputCurrent(chassisPowerSupplyStatus.getInputCurrent());
                            ps.setInputVolts(chassisPowerSupplyStatus.getInputVolts());
                            ps.setOutputRatedPower(chassisPowerSupplyStatus.getOutputRatedPower());
                        }
                    }
                }
            }
        }
        return powerSupplies;
    }
}
