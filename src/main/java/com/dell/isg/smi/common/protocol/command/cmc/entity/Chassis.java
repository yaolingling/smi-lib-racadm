/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

import java.util.ArrayList;
import java.util.List;

import com.dell.isg.smi.common.protocol.command.chassis.entity.ModInfo;

/**
 * @author rahman.muhammad
 *
 */
public class Chassis {

    private int numberOfSlots;
    private int numberOfFreeSlots;

    private List<ChassisCMCViewEntity> chassisCmcList = null;
    private List<ChassisIkvm> chassisIKvmList = null;
    private List<ChassisPCIeEntity> chassisPciList = null;
    private List<ChassisPowerSupplyEntity> chassisPowerSupplyList = null;
    private List<IOModuleEntity> ioModuleEntityList = null;
    private List<ChassisFanEntity> chassisFanList = null;
    private List<ChassisTemperatureSensorEntity> chassisTemperatureSensorList = null;
    private List<ModInfo> serverList = null;
    private List<ModInfo> stashList = null;


    public int getNumberOfSlots() {
        return numberOfSlots;
    }


    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }


    public int getNumberOfFreeSlots() {
        return numberOfFreeSlots;
    }


    public void setNumberOfFreeSlots(int numberOfFreeSlots) {
        this.numberOfFreeSlots = numberOfFreeSlots;
    }


    public List<ChassisCMCViewEntity> getChassisCmcList() {
        if (chassisCmcList == null) {
            chassisCmcList = new ArrayList<ChassisCMCViewEntity>();
        }
        return chassisCmcList;
    }


    public void setChassisCmcList(List<ChassisCMCViewEntity> chassisCmc) {
        this.chassisCmcList = chassisCmc;
    }


    public List<ChassisIkvm> getChassisIKvmList() {

        if (chassisIKvmList == null) {
            chassisIKvmList = new ArrayList<ChassisIkvm>();
        }
        return chassisIKvmList;
    }


    public void setChassisIKvmList(List<ChassisIkvm> chassisIKvm) {
        this.chassisIKvmList = chassisIKvm;
    }


    public List<ChassisFanEntity> getChassisFanList() {
        if (chassisFanList == null) {
            chassisFanList = new ArrayList<ChassisFanEntity>();
        }
        return chassisFanList;
    }


    public void setChassisFanList(List<ChassisFanEntity> chassisFan) {
        this.chassisFanList = chassisFan;
    }


    public List<ChassisTemperatureSensorEntity> getChassisTemperatureSensorList() {

        if (chassisTemperatureSensorList == null) {
            chassisTemperatureSensorList = new ArrayList<ChassisTemperatureSensorEntity>();
        }

        return chassisTemperatureSensorList;
    }


    public void setChassisTemperatureSensorList(List<ChassisTemperatureSensorEntity> chassisTemperatureSensor) {
        this.chassisTemperatureSensorList = chassisTemperatureSensor;
    }


    public List<ChassisPowerSupplyEntity> getChassisPowerSupplyList() {
        if (chassisPowerSupplyList == null) {
            chassisPowerSupplyList = new ArrayList<ChassisPowerSupplyEntity>();
        }
        return chassisPowerSupplyList;
    }


    public void setChassisPowerSupplyList(List<ChassisPowerSupplyEntity> chassisPowerSupply) {
        this.chassisPowerSupplyList = chassisPowerSupply;
    }


    public List<ChassisPCIeEntity> getChassisPciList() {
        if (chassisPciList == null) {
            chassisPciList = new ArrayList<ChassisPCIeEntity>();
        }
        return chassisPciList;
    }


    public void setChassisPciList(List<ChassisPCIeEntity> chassisPci) {
        this.chassisPciList = chassisPci;
    }


    public List<IOModuleEntity> getIoModuleEntityList() {
        if (ioModuleEntityList == null) {
            ioModuleEntityList = new ArrayList<IOModuleEntity>();
        }
        return ioModuleEntityList;
    }


    public void setIoModuleEntityList(List<IOModuleEntity> ioModuleEntity) {
        this.ioModuleEntityList = ioModuleEntity;
    }


    public List<ModInfo> getServerList() {
        if (serverList == null) {
            serverList = new ArrayList<ModInfo>();
        }
        return serverList;
    }


    public void setServerList(List<ModInfo> server) {
        this.serverList = server;
    }


    public List<ModInfo> getStashList() {
        return stashList;
    }


    public void setStashList(List<ModInfo> stashList) {
        this.stashList = stashList;
    }

}
