/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000EPowerSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000ESensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000ESensorInfoBase;
import com.dell.isg.smi.common.protocol.command.chassis.entity.M1000ESensorInfoWithC;
import com.dell.isg.smi.common.protocol.command.chassis.entity.StompPowerSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.StompSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxIntrusionSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxPowerSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxSensorInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxSensorInfoBase;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VrtxSensorInfoWithC;

public class EnumSensorInfoCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumSensorInfoCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumSensorInfoCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("getsensorinfo", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumSensorInfoCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumSensorInfoCmd()");
    }


    /**
     * Gets all of the fan speed sensor info for a vrtx
     * 
     * @return List of VrtxSensorInfo
     * @throws Exception
     */
    public List<VrtxSensorInfoBase> getVrtxFanSpeedSensorInfo() throws Exception {
        List<? extends VrtxSensorInfoBase> vrtxSensorInfoList = null;
        this.setCommand("getsensorinfo -c");
        String xpathExpressionString = String.format("//root/entry[starts-with(sensorName,'Fan-')]|//root/entry[starts-with(sensorName,'Blower-')]");
        try {
            vrtxSensorInfoList = filterUsingXpathForNodeList(VrtxSensorInfoWithC.class, xpathExpressionString);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        if (vrtxSensorInfoList == null || vrtxSensorInfoList.isEmpty()) {
            this.setCommand("getsensorinfo");
            // Here we are looking for Fan or Blower instead of Fan- or Blower- because there is a bug in the VRTX 2.00 firmware
            xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Name,'Fan-')]|//root/entry[starts-with(Sensor_Name,'Blower-')]|" + "//root/entry[starts-with(sensorName,'Fan')]|//root/entry[starts-with(sensorName,'Blower')]");
            vrtxSensorInfoList = filterUsingXpathForNodeList(VrtxSensorInfo.class, xpathExpressionString);
        }

        List<VrtxSensorInfoBase> returnValue = new ArrayList<VrtxSensorInfoBase>();
        returnValue.addAll(vrtxSensorInfoList);
        return returnValue;
    }


    /**
     * Gets all of the temperature sensor info for a vrtx
     * 
     * @return List of VrtxSensorInfo
     * @throws Exception
     */
    public List<VrtxSensorInfoBase> getVrtxTemperatureSensorInfo() throws Exception {
        List<? extends VrtxSensorInfoBase> vrtxSensorInfoList = null;
        this.setCommand("getsensorinfo -c");
        String xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Type,'Temp')]");
        try {
            vrtxSensorInfoList = filterUsingXpathForNodeList(VrtxSensorInfoWithC.class, xpathExpressionString);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        if (vrtxSensorInfoList == null || vrtxSensorInfoList.isEmpty()) {
            this.setCommand("getsensorinfo");
            // Here we are looking for Fan or Blower instead of Fan- or Blower- because there is a bug in the VRTX 2.00 firmware
            xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Type,'Temperature')]|//root/entry[starts-with(senType,'Temp')]");
            vrtxSensorInfoList = filterUsingXpathForNodeList(VrtxSensorInfo.class, xpathExpressionString);
        }

        List<VrtxSensorInfoBase> returnValue = new ArrayList<VrtxSensorInfoBase>();
        returnValue.addAll(vrtxSensorInfoList);
        return returnValue;
    }


    /**
     * Gets all of the power sensor info for a vrtx
     * 
     * @return List of VrtxPowerSensorInfo
     * @throws Exception
     */
    public List<VrtxPowerSensorInfo> getVrtxPowerSensorInfo() throws Exception {
        String xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Type,'Power')]");
        List<VrtxPowerSensorInfo> vrtxPowerSensorInfoList = filterUsingXpathForNodeList(VrtxPowerSensorInfo.class, xpathExpressionString);
        return vrtxPowerSensorInfoList;
    }


    /**
     * Gets the chassis intrusion info for a vrtx
     * 
     * @return List of VrtxIntrusionSensorInfo
     * @throws Exception
     */
    public List<VrtxIntrusionSensorInfo> getVrtxIntrusionSensorInfo() throws Exception {
        String xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Type,'Intrusion')]");
        List<VrtxIntrusionSensorInfo> vrtxIntrusionSensorInfoList = filterUsingXpathForNodeList(VrtxIntrusionSensorInfo.class, xpathExpressionString);
        return vrtxIntrusionSensorInfoList;
    }


    /**
     * Gets all of the fan speed sensor info for a M1000e
     * 
     * @return List of M1000ESensorInfo
     * @throws Exception
     */
    public List<M1000ESensorInfoBase> getM1000eFanSpeedSensorInfo() throws Exception {
        List<? extends M1000ESensorInfoBase> vrtxSensorInfoList = null;
        this.setCommand("getsensorinfo -c");
        String xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Type,'FanSpeed')]");
        try {
            vrtxSensorInfoList = filterUsingXpathForNodeList(M1000ESensorInfoWithC.class, xpathExpressionString);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        if (vrtxSensorInfoList == null || vrtxSensorInfoList.isEmpty()) {
            this.setCommand("getsensorinfo");
            // Here we are looking for Fan or Blower instead of Fan- or Blower- because there is a bug in the VRTX 2.00 firmware
            xpathExpressionString = String.format("//root/entry[starts-with(senType,'FanSpeed')]");
            vrtxSensorInfoList = filterUsingXpathForNodeList(M1000ESensorInfo.class, xpathExpressionString);
        }

        List<M1000ESensorInfoBase> returnValue = new ArrayList<M1000ESensorInfoBase>();
        returnValue.addAll(vrtxSensorInfoList);
        return returnValue;
    }


    /**
     * Gets all of the temperature sensor info for a M1000e
     * 
     * @return List of M1000ESensorInfo
     * @throws Exception
     */
    public List<M1000ESensorInfoBase> getM1000eTemperatureSensorInfo() throws Exception {
        List<? extends M1000ESensorInfoBase> vrtxSensorInfoList = null;
        this.setCommand("getsensorinfo -c");
        String xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Type,'Temp')]");
        try {
            vrtxSensorInfoList = filterUsingXpathForNodeList(M1000ESensorInfoWithC.class, xpathExpressionString);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        if (vrtxSensorInfoList == null || vrtxSensorInfoList.isEmpty()) {
            this.setCommand("getsensorinfo");
            // Here we are looking for Fan or Blower instead of Fan- or Blower- because there is a bug in the VRTX 2.00 firmware
            xpathExpressionString = String.format("//root/entry[starts-with(senType,'Temp')]");
            vrtxSensorInfoList = filterUsingXpathForNodeList(M1000ESensorInfo.class, xpathExpressionString);
        }

        List<M1000ESensorInfoBase> returnValue = new ArrayList<M1000ESensorInfoBase>();
        returnValue.addAll(vrtxSensorInfoList);
        return returnValue;
    }


    /**
     * Gets all of the power sensor info for a M1000e
     * 
     * @return List of M1000EPowerSensorInfo
     * @throws Exception
     */
    public List<M1000EPowerSensorInfo> getM1000ePowerSensorInfo() throws Exception {
        String xpathExpressionString = String.format("//root/entry[starts-with(senType,'PWR')]");
        List<M1000EPowerSensorInfo> m1000EPowerSensorInfoList = filterUsingXpathForNodeList(M1000EPowerSensorInfo.class, xpathExpressionString);
        return m1000EPowerSensorInfoList;
    }


    /**
     * Gets all of the fan speed sensor info for a Stomp
     * 
     * @return List of StompSensorInfo
     * @throws Exception
     */
    public List<StompSensorInfo> getStompFanSpeedSensorInfo() throws Exception {
        this.setCommand("getsensorinfo -c");
        String xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Type,'FanSpeed')]");
        List<StompSensorInfo> stompSensorInfoList = filterUsingXpathForNodeList(StompSensorInfo.class, xpathExpressionString);
        return stompSensorInfoList;
    }


    /**
     * Gets all of the temperature sensor info for a Stomp
     * 
     * @return List of StompSensorInfo
     * @throws Exception
     */
    public List<StompSensorInfo> getStompTemperatureSensorInfo() throws Exception {
        this.setCommand("getsensorinfo -c");
        String xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Type,'Temp')]");
        List<StompSensorInfo> stompSensorInfoList = filterUsingXpathForNodeList(StompSensorInfo.class, xpathExpressionString);
        return stompSensorInfoList;
    }


    /**
     * Gets all of the power sensor info for a Stomp
     * 
     * @return List of StompSensorInfo
     * @throws Exception
     */
    public List<StompPowerSensorInfo> getStompPowerSensorInfo() throws Exception {
        this.setCommand("getsensorinfo -c");
        String xpathExpressionString = String.format("//root/entry[starts-with(Sensor_Type,'Power')]");
        List<StompPowerSensorInfo> stompPowerSensorInfoList = filterUsingXpathForNodeList(StompPowerSensorInfo.class, xpathExpressionString);
        return stompPowerSensorInfoList;
    }
}
