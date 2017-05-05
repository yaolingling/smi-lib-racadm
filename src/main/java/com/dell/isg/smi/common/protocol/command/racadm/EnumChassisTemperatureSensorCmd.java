/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000ESensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000ESensorInfoBase;
import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000ESensorInfoWithC;
import com.dell.isg.smi.common.protocol.command.chassis.entity.StompSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxSensorInfoBase;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxSensorInfoWithC;
import com.dell.isg.smi.common.protocol.command.cmc.entity.ChassisTemperatureSensorEntity;
import com.dell.isg.smi.common.protocol.command.cmc.entity.RacadmCredentials;

public class EnumChassisTemperatureSensorCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumChassisTemperatureSensorCmd.class);
    private RacadmCredentials credentials = null;
    private String ipAddr = null;


    public EnumChassisTemperatureSensorCmd(String ipAddr, String userName, String passwd, boolean check) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumChassisTemperatureSensorCmd(String ipAddr - %s, String userName - %s, String passwd - %s)", ipAddr, userName, "####"));
        }

        this.credentials = new RacadmCredentials();
        this.credentials.setUserName(userName);
        this.credentials.setPassword(passwd);
        this.credentials.setCertificateCheck(check);
        this.ipAddr = ipAddr;

        logger.trace("Exiting constructor: EnumChassisTemperatureSensorCmd()");
    }


    public List<ChassisTemperatureSensorEntity> executeForVrtx() throws Exception {
        EnumSensorInfoCmd enumSensorInfoCmd = new EnumSensorInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        return executeForVrtx(enumSensorInfoCmd);
    }


    public List<ChassisTemperatureSensorEntity> executeForVrtx(EnumSensorInfoCmd enumSensorInfoCmd) throws Exception {
        List<ChassisTemperatureSensorEntity> tempSensorList = new LinkedList<ChassisTemperatureSensorEntity>();
        List<VrtxSensorInfoBase> chassisTempSensorList = null;
        try {
            chassisTempSensorList = enumSensorInfoCmd.getVrtxTemperatureSensorInfo();
        } catch (Exception e) {
            logger.warn("Failed to load the Temperature Sensor information");
            logger.warn(e.getMessage());
        }

        if ((null != chassisTempSensorList) && !chassisTempSensorList.isEmpty()) {
            for (VrtxSensorInfoBase tempSensor : chassisTempSensorList) {
                if (tempSensor instanceof VrtxSensorInfo) {
                    translateVrtxSensorInfoToChassisTemp(tempSensorList, (VrtxSensorInfo) tempSensor);
                } else {
                    translateVrtxSensorInfoWithCToChassisTemp(tempSensorList, (VrtxSensorInfoWithC) tempSensor);
                }
            }
        }
        return tempSensorList;
    }


    private void translateVrtxSensorInfoToChassisTemp(List<ChassisTemperatureSensorEntity> tempSensorList, VrtxSensorInfo tempSensor) {
        ChassisTemperatureSensorEntity tempSensorEntity = new ChassisTemperatureSensorEntity();
        tempSensorEntity.setLc(tempSensor.getLC());
        tempSensorEntity.setLw(tempSensor.getLW());
        tempSensorEntity.setReading(tempSensor.getReading());
        tempSensorEntity.setSensorName(tempSensor.getSensorName());
        tempSensorEntity.setSensorUnits(tempSensor.getSensorUnits());
        tempSensorEntity.setStatus(tempSensor.getStatus());
        tempSensorEntity.setUc(tempSensor.getUC());
        tempSensorEntity.setUw(tempSensor.getUW());
        tempSensorList.add(tempSensorEntity);
    }


    private void translateVrtxSensorInfoWithCToChassisTemp(List<ChassisTemperatureSensorEntity> tempSensorList, VrtxSensorInfoWithC tempSensor) {
        ChassisTemperatureSensorEntity tempSensorEntity = new ChassisTemperatureSensorEntity();
        tempSensorEntity.setLc(tempSensor.getLC());
        tempSensorEntity.setLw(tempSensor.getLW());
        tempSensorEntity.setSensorName(tempSensor.getSensorName());
        if (tempSensor.getUnits() == null || tempSensor.getUnits().isEmpty()) {
            // split reading
            String[] readingAndUnits = tempSensor.getReading().split(" ");
            tempSensorEntity.setSensorUnits(readingAndUnits[1]);
            if (tempSensorEntity.getSensorUnits().equalsIgnoreCase("C")) {
                tempSensorEntity.setSensorUnits("Celsius");
            } else {
                tempSensorEntity.setSensorUnits("Farenheit");
            }
            tempSensorEntity.setReading(readingAndUnits[0]);
        } else {
            tempSensorEntity.setSensorUnits(tempSensor.getUnits());
            tempSensorEntity.setReading(tempSensor.getReading());
        }
        tempSensorEntity.setStatus(tempSensor.getStatus());
        tempSensorEntity.setUc(tempSensor.getUC());
        tempSensorEntity.setUw(tempSensor.getUW());
        tempSensorList.add(tempSensorEntity);
    }


    public List<ChassisTemperatureSensorEntity> executeForM1000e() throws Exception {
        EnumSensorInfoCmd enumSensorInfoCmd = new EnumSensorInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        return executeForM1000e(enumSensorInfoCmd);
    }


    public List<ChassisTemperatureSensorEntity> executeForM1000e(EnumSensorInfoCmd enumSensorInfoCmd) throws Exception {
        List<ChassisTemperatureSensorEntity> tempSensorList = new LinkedList<ChassisTemperatureSensorEntity>();
        List<M1000ESensorInfoBase> chassisTempSensorList = null;
        try {
            chassisTempSensorList = enumSensorInfoCmd.getM1000eTemperatureSensorInfo();
        } catch (Exception e) {
            logger.warn("Failed to load the Temperature Sensor information");
            logger.warn(e.getMessage());
        }

        if ((null != chassisTempSensorList) && !chassisTempSensorList.isEmpty()) {
            for (M1000ESensorInfoBase tempSensor : chassisTempSensorList) {
                if (tempSensor instanceof M1000ESensorInfo) {
                    translateM1000eSensorInfoToChassisTemp(tempSensorList, (M1000ESensorInfo) tempSensor);
                } else {
                    translateM1000eSensorInfoWithCToChassisTemp(tempSensorList, (M1000ESensorInfoWithC) tempSensor);
                }
            }
        }
        return tempSensorList;
    }


    private void translateM1000eSensorInfoToChassisTemp(List<ChassisTemperatureSensorEntity> tempSensorList, M1000ESensorInfo tempSensor) {
        ChassisTemperatureSensorEntity tempSensorEntity = new ChassisTemperatureSensorEntity();
        tempSensorEntity.setLc(tempSensor.getLc());
        tempSensorEntity.setNum(tempSensor.getNum());
        tempSensorEntity.setReading(tempSensor.getReading());
        tempSensorEntity.setSensorName(tempSensor.getSensorName());
        tempSensorEntity.setSensorUnits(tempSensor.getUnits());
        tempSensorEntity.setStatus(tempSensor.getStatus());
        tempSensorEntity.setUc(tempSensor.getUc());
        tempSensorList.add(tempSensorEntity);
    }


    private void translateM1000eSensorInfoWithCToChassisTemp(List<ChassisTemperatureSensorEntity> tempSensorList, M1000ESensorInfoWithC tempSensor) {
        ChassisTemperatureSensorEntity tempSensorEntity = new ChassisTemperatureSensorEntity();
        tempSensorEntity.setNum(tempSensor.getNum());
        tempSensorEntity.setSensorName(tempSensor.getSensorName());
        // split reading
        String[] readingAndUnits = tempSensor.getReading().split(" ");
        tempSensorEntity.setSensorUnits(readingAndUnits[1]);
        if (tempSensorEntity.getSensorUnits().equalsIgnoreCase("C")) {
            tempSensorEntity.setSensorUnits("Celsius");
        } else {
            tempSensorEntity.setSensorUnits("Farenheit");
        }
        tempSensorEntity.setReading(readingAndUnits[0]);
        tempSensorEntity.setStatus(tempSensor.getStatus());
        tempSensorEntity.setLc(tempSensor.getLC());
        tempSensorEntity.setUc(tempSensor.getUC());
        tempSensorList.add(tempSensorEntity);
    }


    public List<ChassisTemperatureSensorEntity> executeForStomp() throws Exception {
        EnumSensorInfoCmd enumSensorInfoCmd = new EnumSensorInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        return executeForStomp(enumSensorInfoCmd);
    }


    public List<ChassisTemperatureSensorEntity> executeForStomp(EnumSensorInfoCmd enumSensorInfoCmd) throws Exception {
        List<ChassisTemperatureSensorEntity> tempSensorList = new LinkedList<ChassisTemperatureSensorEntity>();
        List<StompSensorInfo> chassisTempSensorList = null;
        try {
            chassisTempSensorList = enumSensorInfoCmd.getStompTemperatureSensorInfo();
        } catch (Exception e) {
            logger.warn("Failed to load the Temperature Sensor information");
            logger.warn(e.getMessage());
        }

        if ((null != chassisTempSensorList) && !chassisTempSensorList.isEmpty()) {
            for (StompSensorInfo tempSensor : chassisTempSensorList) {
                ChassisTemperatureSensorEntity tempSensorEntity = new ChassisTemperatureSensorEntity();
                tempSensorEntity.setLc(tempSensor.getLC());
                tempSensorEntity.setLw(tempSensor.getLW());
                tempSensorEntity.setSensorName(tempSensor.getSensorName());
                if (tempSensor.getUnits() == null || tempSensor.getUnits().isEmpty()) {
                    // split reading
                    String[] readingAndUnits = tempSensor.getReading().split(" ");
                    tempSensorEntity.setSensorUnits(readingAndUnits[1]);
                    if (tempSensorEntity.getSensorUnits().equalsIgnoreCase("C")) {
                        tempSensorEntity.setSensorUnits("Celsius");
                    } else {
                        tempSensorEntity.setSensorUnits("Farenheit");
                    }
                    tempSensorEntity.setReading(readingAndUnits[0]);
                } else {
                    tempSensorEntity.setSensorUnits(tempSensor.getUnits());
                    tempSensorEntity.setReading(tempSensor.getReading());
                }
                tempSensorEntity.setStatus(tempSensor.getStatus());
                tempSensorEntity.setUc(tempSensor.getUC());
                tempSensorEntity.setUw(tempSensor.getUW());
                tempSensorList.add(tempSensorEntity);
            }
        }
        return tempSensorList;
    }

}
