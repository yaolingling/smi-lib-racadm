/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

import java.io.Serializable;

public class ModularChassisViewEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String assetTag = null; // m1000e, plasma
    private String cmcModel = null; // plasma
    private String caption = null; // m1000e, plasma
    private String chassisDefaultLowerPowerCap = null; // m1000e
    private String chassisDefaultUpperPowerCap = null; // m1000e
    private String chassisExternalPowerCap = null; // m1000e
    private String description = null; // m1000e, plasma
    private String dnsDomainName = null; // plasma
    private String elementName = null; // m1000e, plasma
    private String enhancedCoolingMode = null; // plasma
    private String expressServiceCode = null; // plasma
    private String FQDD = null; // m1000e, plasma
    private String flexFabricState = null; // m1000e, plasma
    private String flexFabricStateDescription = null; // m1000e, plasma
    private String generation = null; // m1000e, plasma
    private String hostName = null; // m1000e, plasma
    private String ipv4Address = null; // m1000e, plasma
    private String instanceID = null; // m1000e, plasma
    private String location = null; // m1000e, plasma
    private String mgmtContollerFirmwareVersion = null; // m1000e, plasma
    private String model = null; // plasma
    private String physicalLocationAisle = null; // m1000e, plasma
    private String physicalLocationChassisName = null; // m1000e, plasma
    private String physicalLocationDataCenter = null; // m1000e, plasma
    private String physicalLocationDeviceSize = null; // m1000e, plasma
    private String physicalLocationRack = null; // m1000e, plasma
    private String physicalLocationRackSlot = null; // m1000e, plasma
    private String powerState = null; // m1000e, plasma
    private String primaryStatus = null; // m1000e, plasma
    private String pwrInputInfrastructureAllocation = null; // m1000e, plasma
    private String pwrInputSystemConsumption = null; // m1000e, plasma
    private String SNMPCommunityBladeIRAlert = null; // m1000e, plasma
    private String SNMPDestinationBladeIRAlert = null; // m1000e, plasma
    private String serverBasedPowerMgmtEnableTime = null; // m1000e, plasma
    private String serverBasedPowerMgmtEnabled = null; // m1000e, plasma
    private String serviceTag = null; // m1000e, plasma
    private String systemID = null; // plasma
    private String systemPSUInputPower = null; // m1000e
    private String systemPSUOutputPower = null; // m1000e
    private String urlString = null; // plasma
    String useHostNameForSlotName = null; // m1000e, plasma


    /**
     * @return the assetTag
     */
    public String getAssetTag() {
        return assetTag;
    }


    /**
     * @param assetTag the assetTag to set
     */
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }


    /**
     * @return the cmcModel
     */
    public String getCmcModel() {
        return cmcModel;
    }


    /**
     * @param cmcModel the cmcModel to set
     */
    public void setCmcModel(String cmcModel) {
        this.cmcModel = cmcModel;
    }


    /**
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }


    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }


    /**
     * @return the chassisDefaultLowerPowerCap
     */
    public String getChassisDefaultLowerPowerCap() {
        return chassisDefaultLowerPowerCap;
    }


    /**
     * @param chassisDefaultLowerPowerCap the chassisDefaultLowerPowerCap to set
     */
    public void setChassisDefaultLowerPowerCap(String chassisDefaultLowerPowerCap) {
        this.chassisDefaultLowerPowerCap = chassisDefaultLowerPowerCap;
    }


    /**
     * @return the chassisDefaultUpperPowerCap
     */
    public String getChassisDefaultUpperPowerCap() {
        return chassisDefaultUpperPowerCap;
    }


    /**
     * @param chassisDefaultUpperPowerCap the chassisDefaultUpperPowerCap to set
     */
    public void setChassisDefaultUpperPowerCap(String chassisDefaultUpperPowerCap) {
        this.chassisDefaultUpperPowerCap = chassisDefaultUpperPowerCap;
    }


    /**
     * @return the chassisExternalPowerCap
     */
    public String getChassisExternalPowerCap() {
        return chassisExternalPowerCap;
    }


    /**
     * @param chassisExternalPowerCap the chassisExternalPowerCap to set
     */
    public void setChassisExternalPowerCap(String chassisExternalPowerCap) {
        this.chassisExternalPowerCap = chassisExternalPowerCap;
    }


    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return the dnsDomainName
     */
    public String getDnsDomainName() {
        return dnsDomainName;
    }


    /**
     * @param dnsDomainName the dnsDomainName to set
     */
    public void setDnsDomainName(String dnsDomainName) {
        this.dnsDomainName = dnsDomainName;
    }


    /**
     * @return the elementName
     */
    public String getElementName() {
        return elementName;
    }


    /**
     * @param elementName the elementName to set
     */
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }


    /**
     * @return the enhancedCoolingMode
     */
    public String getEnhancedCoolingMode() {
        return enhancedCoolingMode;
    }


    /**
     * @param enhancedCoolingMode the enhancedCoolingMode to set
     */
    public void setEnhancedCoolingMode(String enhancedCoolingMode) {
        this.enhancedCoolingMode = enhancedCoolingMode;
    }


    /**
     * @return the expressServiceCode
     */
    public String getExpressServiceCode() {
        return expressServiceCode;
    }


    /**
     * @param expressServiceCode the expressServiceCode to set
     */
    public void setExpressServiceCode(String expressServiceCode) {
        this.expressServiceCode = expressServiceCode;
    }


    /**
     * @return the fQDD
     */
    public String getFQDD() {
        return FQDD;
    }


    /**
     * @param fQDD the fQDD to set
     */
    public void setFQDD(String fQDD) {
        FQDD = fQDD;
    }


    /**
     * @return the flexFabricState
     */
    public String isFlexFabricState() {
        return flexFabricState;
    }


    /**
     * @param flexFabricState the flexFabricState to set
     */
    public void setFlexFabricState(String flexFabricState) {
        this.flexFabricState = flexFabricState;
    }


    /**
     * @return the flexFabricStateDescription
     */
    public String getFlexFabricStateDescription() {
        return flexFabricStateDescription;
    }


    /**
     * @param flexFabricStateDescription the flexFabricStateDescription to set
     */
    public void setFlexFabricStateDescription(String flexFabricStateDescription) {
        this.flexFabricStateDescription = flexFabricStateDescription;
    }


    /**
     * @return the generation
     */
    public String getGeneration() {
        return generation;
    }


    /**
     * @param generation the generation to set
     */
    public void setGeneration(String generation) {
        this.generation = generation;
    }


    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }


    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }


    /**
     * @return the ipv4Address
     */
    public String getIpv4Address() {
        return ipv4Address;
    }


    /**
     * @param ipv4Address the ipv4Address to set
     */
    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }


    /**
     * @return the instanceID
     */
    public String getInstanceID() {
        return instanceID;
    }


    /**
     * @param instanceID the instanceID to set
     */
    public void setInstanceID(String instanceID) {
        this.instanceID = instanceID;
    }


    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }


    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * @return the mgmtContollerFirmwareVersion
     */
    public String getMgmtContollerFirmwareVersion() {
        return mgmtContollerFirmwareVersion;
    }


    /**
     * @param mgmtContollerFirmwareVersion the mgmtContollerFirmwareVersion to set
     */
    public void setMgmtContollerFirmwareVersion(String mgmtContollerFirmwareVersion) {
        this.mgmtContollerFirmwareVersion = mgmtContollerFirmwareVersion;
    }


    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }


    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }


    /**
     * @return the physicalLocationAisle
     */
    public String getPhysicalLocationAisle() {
        return physicalLocationAisle;
    }


    /**
     * @param physicalLocationAisle the physicalLocationAisle to set
     */
    public void setPhysicalLocationAisle(String physicalLocationAisle) {
        this.physicalLocationAisle = physicalLocationAisle;
    }


    /**
     * @return the physicalLocationChassisName
     */
    public String getPhysicalLocationChassisName() {
        return physicalLocationChassisName;
    }


    /**
     * @param physicalLocationChassisName the physicalLocationChassisName to set
     */
    public void setPhysicalLocationChassisName(String physicalLocationChassisName) {
        this.physicalLocationChassisName = physicalLocationChassisName;
    }


    /**
     * @return the physicalLocationDataCenter
     */
    public String getPhysicalLocationDataCenter() {
        return physicalLocationDataCenter;
    }


    /**
     * @param physicalLocationDataCenter the physicalLocationDataCenter to set
     */
    public void setPhysicalLocationDataCenter(String physicalLocationDataCenter) {
        this.physicalLocationDataCenter = physicalLocationDataCenter;
    }


    /**
     * @return the physicalLocationDeviceSize
     */
    public String getPhysicalLocationDeviceSize() {
        return physicalLocationDeviceSize;
    }


    /**
     * @param physicalLocationDeviceSize the physicalLocationDeviceSize to set
     */
    public void setPhysicalLocationDeviceSize(String physicalLocationDeviceSize) {
        this.physicalLocationDeviceSize = physicalLocationDeviceSize;
    }


    /**
     * @return the physicalLocationRack
     */
    public String getPhysicalLocationRack() {
        return physicalLocationRack;
    }


    /**
     * @param physicalLocationRack the physicalLocationRack to set
     */
    public void setPhysicalLocationRack(String physicalLocationRack) {
        this.physicalLocationRack = physicalLocationRack;
    }


    /**
     * @return the physicalLocationRackSlot
     */
    public String getPhysicalLocationRackSlot() {
        return physicalLocationRackSlot;
    }


    /**
     * @param physicalLocationRackSlot the physicalLocationRackSlot to set
     */
    public void setPhysicalLocationRackSlot(String physicalLocationRackSlot) {
        this.physicalLocationRackSlot = physicalLocationRackSlot;
    }


    /**
     * @return the powerState
     */
    public String getPowerState() {
        return powerState;
    }


    /**
     * @param powerState the powerState to set
     */
    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }


    /**
     * @return the primaryStatus
     */
    public String getPrimaryStatus() {
        return primaryStatus;
    }


    /**
     * @param primaryStatus the primaryStatus to set
     */
    public void setPrimaryStatus(String primaryStatus) {
        this.primaryStatus = primaryStatus;
    }


    /**
     * @return the pwrInputInfrastructureAllocation
     */
    public String getPwrInputInfrastructureAllocation() {
        return pwrInputInfrastructureAllocation;
    }


    /**
     * @param pwrInputInfrastructureAllocation the pwrInputInfrastructureAllocation to set
     */
    public void setPwrInputInfrastructureAllocation(String pwrInputInfrastructureAllocation) {
        this.pwrInputInfrastructureAllocation = pwrInputInfrastructureAllocation;
    }


    /**
     * @return the pwrInputSystemConsumption
     */
    public String getPwrInputSystemConsumption() {
        return pwrInputSystemConsumption;
    }


    /**
     * @param pwrInputSystemConsumption the pwrInputSystemConsumption to set
     */
    public void setPwrInputSystemConsumption(String pwrInputSystemConsumption) {
        this.pwrInputSystemConsumption = pwrInputSystemConsumption;
    }


    /**
     * @return the sNMPCommunityBladeIRAlert
     */
    public String getSNMPCommunityBladeIRAlert() {
        return SNMPCommunityBladeIRAlert;
    }


    /**
     * @param sNMPCommunityBladeIRAlert the sNMPCommunityBladeIRAlert to set
     */
    public void setSNMPCommunityBladeIRAlert(String sNMPCommunityBladeIRAlert) {
        SNMPCommunityBladeIRAlert = sNMPCommunityBladeIRAlert;
    }


    /**
     * @return the sNMPDestinationBladeIRAlert
     */
    public String getSNMPDestinationBladeIRAlert() {
        return SNMPDestinationBladeIRAlert;
    }


    /**
     * @param sNMPDestinationBladeIRAlert the sNMPDestinationBladeIRAlert to set
     */
    public void setSNMPDestinationBladeIRAlert(String sNMPDestinationBladeIRAlert) {
        SNMPDestinationBladeIRAlert = sNMPDestinationBladeIRAlert;
    }


    /**
     * @return the serverBasedPowerMgmtEnableTime
     */
    public String getServerBasedPowerMgmtEnableTime() {
        return serverBasedPowerMgmtEnableTime;
    }


    /**
     * @param serverBasedPowerMgmtEnableTime the serverBasedPowerMgmtEnableTime to set
     */
    public void setServerBasedPowerMgmtEnableTime(String serverBasedPowerMgmtEnableTime) {
        this.serverBasedPowerMgmtEnableTime = serverBasedPowerMgmtEnableTime;
    }


    /**
     * @return the serverBasedPowerMgmtEnabled
     */
    public String isServerBasedPowerMgmtEnabled() {
        return serverBasedPowerMgmtEnabled;
    }


    /**
     * @param serverBasedPowerMgmtEnabled the serverBasedPowerMgmtEnabled to set
     */
    public void setServerBasedPowerMgmtEnabled(String serverBasedPowerMgmtEnabled) {
        this.serverBasedPowerMgmtEnabled = serverBasedPowerMgmtEnabled;
    }


    /**
     * @return the serviceTag
     */
    public String getServiceTag() {
        return serviceTag;
    }


    /**
     * @param serviceTag the serviceTag to set
     */
    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }


    /**
     * @return the systemID
     */
    public String getSystemID() {
        return systemID;
    }


    /**
     * @param systemID the systemID to set
     */
    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }


    /**
     * @return the systemPSUInputPower
     */
    public String getSystemPSUInputPower() {
        return systemPSUInputPower;
    }


    /**
     * @param systemPSUInputPower the systemPSUInputPower to set
     */
    public void setSystemPSUInputPower(String systemPSUInputPower) {
        this.systemPSUInputPower = systemPSUInputPower;
    }


    /**
     * @return the systemPSUOutputPower
     */
    public String getSystemPSUOutputPower() {
        return systemPSUOutputPower;
    }


    /**
     * @param systemPSUOutputPower the systemPSUOutputPower to set
     */
    public void setSystemPSUOutputPower(String systemPSUOutputPower) {
        this.systemPSUOutputPower = systemPSUOutputPower;
    }


    /**
     * @return the urlString
     */
    public String getUrlString() {
        return urlString;
    }


    /**
     * @param urlString the urlString to set
     */
    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }


    /**
     * @return the useHostNameForSlotName
     */
    public String isUseHostNameForSlotName() {
        return useHostNameForSlotName;
    }


    /**
     * @param useHostNameForSlotName the useHostNameForSlotName to set
     */
    public void setUseHostNameForSlotName(String useHostNameForSlotName) {
        this.useHostNameForSlotName = useHostNameForSlotName;
    }

}
