/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

public class ChassisPCIeEntity {
    private String slotNumber;
    private String slotName;
    private String fabric;
    private String serverMapping;
    private String serverSlot;
    private String powerState;
    private String adapterPresent;
    private String assignmentStatus;
    private String allocatedSlotPower;
    private String slotType;
    private String pciDeviceId;
    private String pciVendorId;


    public String getSlotNumber() {
        return slotNumber;
    }


    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }


    public String getSlotName() {
        return slotName;
    }


    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }


    public String getFabric() {
        return fabric;
    }


    public void setFabric(String fabric) {
        this.fabric = fabric;
    }


    public String getPowerState() {
        return powerState;
    }


    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }


    public String getServerMapping() {
        return serverMapping;
    }


    public void setServerMapping(String serverMapping) {
        this.serverMapping = serverMapping;
    }


    public String getServerSlot() {
        return serverSlot;
    }


    public void setServerSlot(String serverSlot) {
        this.serverSlot = serverSlot;
    }


    public String getAdapterPresent() {
        return adapterPresent;
    }


    public void setAdapterPresent(String adapterPresent) {
        this.adapterPresent = adapterPresent;
    }


    public String getAssignmentStatus() {
        return assignmentStatus;
    }


    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }


    public String getAllocatedSlotPower() {
        return allocatedSlotPower;
    }


    public void setAllocatedSlotPower(String allocatedSlotPower) {
        this.allocatedSlotPower = allocatedSlotPower;
    }


    public String getSlotType() {
        return slotType;
    }


    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }


    public String getPciDeviceId() {
        return pciDeviceId;
    }


    public void setPciDeviceId(String pciDeviceId) {
        this.pciDeviceId = pciDeviceId;
    }


    public String getPciVendorId() {
        return pciVendorId;
    }


    public void setPciVendorId(String pciVendorId) {
        this.pciVendorId = pciVendorId;
    }
}
