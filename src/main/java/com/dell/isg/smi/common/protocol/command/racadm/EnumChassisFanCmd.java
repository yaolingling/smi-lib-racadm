/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000ESensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000ESensorInfoBase;
import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000ESensorInfoWithC;
import com.dell.isg.smi.common.protocol.command.chassis.entity.ModInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.StompSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxSensorInfoBase;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxSensorInfoWithC;
import com.dell.isg.smi.common.protocol.command.cmc.entity.ChassisFanEntity;
import com.dell.isg.smi.common.protocol.command.cmc.entity.RacadmCredentials;
import com.dell.isg.smi.common.protocol.command.racadm.EnumModInfoCmd.ModInfoTypeEnum;

public class EnumChassisFanCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumChassisFanCmd.class);
    private RacadmCredentials credentials = null;
    private String ipAddr = null;


    public EnumChassisFanCmd(String ipAddr, String userName, String passwd, boolean check) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumChassisFanCmd(String ipAddr - %s, String userName - %s, String passwd - %s)", ipAddr, userName, "####"));
        }

        this.credentials = new RacadmCredentials();
        this.credentials.setUserName(userName);
        this.credentials.setPassword(passwd);
        this.credentials.setCertificateCheck(check);
        this.ipAddr = ipAddr;

        logger.trace("Exiting constructor: EnumChassisFanCmd()");
    }


    private List<ChassisFanEntity> parseModuleInfoInfoResponse(List<ModInfo> modInfoList) {
        List<ChassisFanEntity> fans = new LinkedList<ChassisFanEntity>();
        if ((null != modInfoList) && !modInfoList.isEmpty()) {
            for (ModInfo modInfo : modInfoList) {
                ChassisFanEntity fan = new ChassisFanEntity();
                fan.setName(modInfo.getModule());
                fan.setPowerState(modInfo.getPwrState());
                fan.setHealth(modInfo.getHealth());
                String presence = modInfo.getPresence();
                if (presence != null && presence.equalsIgnoreCase("present")) {
                    fan.setPresent(true);
                }
                fans.add(fan);
            }
        }
        return fans;
    }


    public List<ChassisFanEntity> executeForVrtx() throws Exception {
        EnumModInfoCmd enumModInfoCmd = new EnumModInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        EnumSensorInfoCmd enumSensorInfoCmd = new EnumSensorInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        return executeForVrtx(enumModInfoCmd, enumSensorInfoCmd);
    }


    public List<ChassisFanEntity> executeForVrtx(EnumModInfoCmd enumModInfoCmd, EnumSensorInfoCmd enumSensorInfoCmd) throws Exception {
        // Send the get module info command to load all the modules.
        List<ModInfo> modInfoList = enumModInfoCmd.getModInfoEntitiesByType(ModInfoTypeEnum.FAN);
        modInfoList.addAll(enumModInfoCmd.getModInfoEntitiesByType(ModInfoTypeEnum.BLOWER));

        List<ChassisFanEntity> finalList = new ArrayList<ChassisFanEntity>();
        List<ChassisFanEntity> fansList = this.parseModuleInfoInfoResponse(modInfoList);
        List<VrtxSensorInfoBase> chassisFanSensorList = null;
        try {
            chassisFanSensorList = enumSensorInfoCmd.getVrtxFanSpeedSensorInfo();
        } catch (Exception e) {
            logger.warn("Failed to load the Fan information");
            logger.warn(e.getMessage());
        }

        if ((null != chassisFanSensorList) && !chassisFanSensorList.isEmpty()) {
            for (VrtxSensorInfoBase fanSensor : chassisFanSensorList) {
                if ((null != fansList) && !fansList.isEmpty()) {
                    for (ChassisFanEntity fan : fansList) {
                        if (fanSensor instanceof VrtxSensorInfo) {
                            if (translateVrtxSensorInfoToChassisFan(finalList, (VrtxSensorInfo) fanSensor, fan)) {
                                break;
                            }
                        } else {
                            if (translateVrtxSensorInfoWithCToChassisFan(finalList, (VrtxSensorInfoWithC) fanSensor, fan)) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        return finalList;
    }


    private boolean translateVrtxSensorInfoToChassisFan(List<ChassisFanEntity> finalList, VrtxSensorInfo fanSensor, ChassisFanEntity fan) {
        boolean found = false;
        // Need to add -number to sensorName because there is a bug in VRTX 2.00 firmware
        if (!fanSensor.getSensorName().contains("-")) {
            fanSensor.setSensorName(String.format("{0}-{1}", fanSensor.getSensorName(), fanSensor.getNum()));
        }
        if (fanSensor.getSensorName().toLowerCase().contains(fan.getName().toLowerCase())) {
            found = true;
            if (fan.getName().equalsIgnoreCase(fanSensor.getSensorName())) {
                fan.setLc(fanSensor.getLC());
                fan.setLw(fanSensor.getLW());
                fan.setStatus(fanSensor.getStatus());
                fan.setUc(fanSensor.getUC());
                fan.setUw(fanSensor.getUW());
                fan.setUnits(fanSensor.getSensorUnits());
                fan.setReading(fanSensor.getReading());
                finalList.add(fan);
            } else {
                ChassisFanEntity fan1 = new ChassisFanEntity();
                fan1.setHealth(fan.getHealth());
                fan1.setPowerState(fan.getPowerState());
                fan1.setPresent(fan.isPresent());
                fan1.setLc(fanSensor.getLC());
                fan1.setLw(fanSensor.getLW());
                fan1.setStatus(fanSensor.getStatus());
                fan1.setUc(fanSensor.getUC());
                fan1.setUw(fanSensor.getUW());
                fan1.setUnits(fanSensor.getSensorUnits());
                fan1.setReading(fanSensor.getReading());
                fan1.setName(fanSensor.getSensorName());
                finalList.add(fan1);
            }
        }
        return found;
    }


    private boolean translateVrtxSensorInfoWithCToChassisFan(List<ChassisFanEntity> finalList, VrtxSensorInfoWithC fanSensor, ChassisFanEntity fan) {
        boolean found = false;
        if (fanSensor.getSensorName().toLowerCase().contains(fan.getName().toLowerCase())) {
            found = true;
            if (fan.getName().equalsIgnoreCase(fanSensor.getSensorName())) {
                fan.setLc(fanSensor.getLC());
                fan.setLw(fanSensor.getLW());
                fan.setStatus(fanSensor.getStatus());
                fan.setUc(fanSensor.getUC());
                fan.setUw(fanSensor.getUW());
                if (fanSensor.getUnits() == null || fanSensor.getUnits().isEmpty()) {
                    // split reading
                    String[] readingAndUnits = fanSensor.getReading().split(" ");
                    fan.setUnits(readingAndUnits[1]);
                    fan.setReading(readingAndUnits[0]);
                } else {
                    fan.setUnits(fanSensor.getUnits());
                    fan.setReading(fanSensor.getReading());
                }

                finalList.add(fan);
            } else {
                ChassisFanEntity fan1 = new ChassisFanEntity();
                fan1.setHealth(fan.getHealth());
                fan1.setPowerState(fan.getPowerState());
                fan1.setPresent(fan.isPresent());
                fan1.setLc(fanSensor.getLC());
                fan1.setLw(fanSensor.getLW());
                fan1.setStatus(fanSensor.getStatus());
                fan1.setUc(fanSensor.getUC());
                fan1.setUw(fanSensor.getUW());
                if (fanSensor.getUnits() == null || fanSensor.getUnits().isEmpty()) {
                    // split reading
                    String[] readingAndUnits = fanSensor.getReading().split(" ");
                    fan1.setUnits(readingAndUnits[1]);
                    fan1.setReading(readingAndUnits[0]);
                } else {
                    fan1.setUnits(fanSensor.getUnits());
                    fan1.setReading(fanSensor.getReading());
                }
                fan1.setName(fanSensor.getSensorName());
                finalList.add(fan1);
            }
        }
        return found;
    }


    public List<ChassisFanEntity> executeForM1000e() throws Exception {
        EnumModInfoCmd enumModInfoCmd = new EnumModInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        EnumSensorInfoCmd enumSensorInfoCmd = new EnumSensorInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        return executeForM1000e(enumModInfoCmd, enumSensorInfoCmd);
    }


    public List<ChassisFanEntity> executeForM1000e(EnumModInfoCmd enumModInfoCmd, EnumSensorInfoCmd enumSensorInfoCmd) throws Exception {
        // Send the get module info command to load all the modules.
        List<ModInfo> modInfoList = enumModInfoCmd.getModInfoEntitiesByType(ModInfoTypeEnum.FAN);
        List<ChassisFanEntity> fansList = this.parseModuleInfoInfoResponse(modInfoList);
        List<M1000ESensorInfoBase> chassisFanSensorList = null;
        try {
            chassisFanSensorList = enumSensorInfoCmd.getM1000eFanSpeedSensorInfo();
        } catch (Exception e) {
            logger.warn("Failed to load the Fan information");
            logger.warn(e.getMessage());
        }

        // Set the power budget information.
        if ((null != fansList) && !fansList.isEmpty()) {
            for (ChassisFanEntity fan : fansList) {
                if ((null != chassisFanSensorList) && !chassisFanSensorList.isEmpty()) {
                    for (M1000ESensorInfoBase fanSensor : chassisFanSensorList) {
                        if (fan.getName().equalsIgnoreCase(fanSensor.getSensorName())) {
                            if (fanSensor instanceof M1000ESensorInfo) {
                                translateM1000eSensorInfoToChassisFan(fan, (M1000ESensorInfo) fanSensor);
                            } else {
                                translateM1000eSensorInfoWithCToChassisFan(fan, (M1000ESensorInfoWithC) fanSensor);
                            }
                            break;
                        }
                    }
                }
            }
        }
        return fansList;
    }


    private void translateM1000eSensorInfoToChassisFan(ChassisFanEntity fan, M1000ESensorInfo fanSensor) {
        fan.setLc(fanSensor.getLc());
        fan.setStatus(fanSensor.getStatus());
        fan.setUc(fanSensor.getUc());
        fan.setUnits(fanSensor.getUnits());
        fan.setReading(fanSensor.getReading());
    }


    private void translateM1000eSensorInfoWithCToChassisFan(ChassisFanEntity fan, M1000ESensorInfoWithC fanSensor) {
        fan.setLc(fanSensor.getLC());
        fan.setStatus(fanSensor.getStatus());
        fan.setUc(fanSensor.getUC());
        // split reading
        String[] readingAndUnits = fanSensor.getReading().split(" ");
        fan.setUnits(readingAndUnits[1]);
        fan.setReading(readingAndUnits[0]);
    }


    public List<ChassisFanEntity> executeForStomp() throws Exception {
        EnumModInfoCmd enumModInfoCmd = new EnumModInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        EnumSensorInfoCmd enumSensorInfoCmd = new EnumSensorInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        return executeForStomp(enumModInfoCmd, enumSensorInfoCmd);
    }


    public List<ChassisFanEntity> executeForStomp(EnumModInfoCmd enumModInfoCmd, EnumSensorInfoCmd enumSensorInfoCmd) throws Exception {
        // Send the get module info command to load all the modules.
        List<ModInfo> modInfoList = enumModInfoCmd.getModInfoEntitiesByType(ModInfoTypeEnum.FAN);
        modInfoList.addAll(enumModInfoCmd.getModInfoEntitiesByType(ModInfoTypeEnum.BLOWER));

        List<ChassisFanEntity> finalList = new ArrayList<ChassisFanEntity>();
        List<ChassisFanEntity> fansList = this.parseModuleInfoInfoResponse(modInfoList);
        List<StompSensorInfo> chassisFanSensorList = null;
        try {
            chassisFanSensorList = enumSensorInfoCmd.getStompFanSpeedSensorInfo();
        } catch (Exception e) {
            logger.warn("Failed to load the Fan information");
            logger.warn(e.getMessage());
        }

        if ((null != chassisFanSensorList) && !chassisFanSensorList.isEmpty()) {
            for (StompSensorInfo fanSensor : chassisFanSensorList) {
                if ((null != fansList) && !fansList.isEmpty()) {
                    for (ChassisFanEntity fan : fansList) {
                        if (fanSensor.getSensorName().toLowerCase().contains(fan.getName().toLowerCase())) {
                            if (fan.getName().equalsIgnoreCase(fanSensor.getSensorName())) {
                                fan.setLc(fanSensor.getLC());
                                fan.setStatus(fanSensor.getStatus());
                                fan.setLw(fanSensor.getLW());
                                if (fanSensor.getUnits() == null || fanSensor.getUnits().isEmpty()) {
                                    // split reading
                                    String[] readingAndUnits = fanSensor.getReading().split(" ");
                                    fan.setUnits(readingAndUnits[1]);
                                    fan.setReading(readingAndUnits[0]);
                                } else {
                                    fan.setUnits(fanSensor.getUnits());
                                    fan.setReading(fanSensor.getReading());
                                }
                                finalList.add(fan);
                            } else {
                                ChassisFanEntity fan1 = new ChassisFanEntity();
                                fan1.setHealth(fan.getHealth());
                                fan1.setName(fan.getName());
                                fan1.setPowerState(fan.getPowerState());
                                fan1.setPresent(fan.isPresent());
                                fan1.setLw(fanSensor.getLW());
                                fan1.setStatus(fanSensor.getStatus());
                                fan1.setLc(fanSensor.getLC());
                                if (fanSensor.getUnits() == null || fanSensor.getUnits().isEmpty()) {
                                    // split reading
                                    String[] readingAndUnits = fanSensor.getReading().split(" ");
                                    fan1.setUnits(readingAndUnits[1]);
                                    fan1.setReading(readingAndUnits[0]);
                                } else {
                                    fan1.setUnits(fanSensor.getUnits());
                                    fan1.setReading(fanSensor.getReading());
                                }
                                fan1.setName(fanSensor.getSensorName());
                                finalList.add(fan1);
                            }
                            break;
                        }
                    }
                }
            }
        }

        return finalList;
    }

}
