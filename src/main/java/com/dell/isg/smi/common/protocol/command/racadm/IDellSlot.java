/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

public interface IDellSlot extends IBaseElement {

    /**
     * @return the connectorDescription
     */
    public abstract String getConnectorDescription();


    /**
     * @param connectorDescription the connectorDescription to set
     */
    public abstract void setConnectorDescription(String connectorDescription);


    /**
     * @return the connectorGender
     */
    public abstract String getConnectorGender();


    /**
     * @param connectorGender the connectorGender to set
     */
    public abstract void setConnectorGender(String connectorGender);


    /**
     * @return the connectorLayout
     */
    public abstract String getConnectorLayout();


    /**
     * @param connectorLayout the connectorLayout to set
     */
    public abstract void setConnectorLayout(String connectorLayout);


    /**
     * @return the connectorPinout
     */
    public abstract String getConnectorPinout();


    /**
     * @param connectorPinout the connectorPinout to set
     */
    public abstract void setConnectorPinout(String connectorPinout);


    /**
     * @return the heightAllowed
     */
    public abstract String getHeightAllowed();


    /**
     * @param heightAllowed the heightAllowed to set
     */
    public abstract void setHeightAllowed(String heightAllowed);


    /**
     * @return the lengthAllowed
     */
    public abstract String getLengthAllowed();


    /**
     * @param lengthAllowed the lengthAllowed to set
     */
    public abstract void setLengthAllowed(String lengthAllowed);


    /**
     * @return the maxDataWidth
     */
    public abstract String getMaxDataWidth();


    /**
     * @param maxDataWidth the maxDataWidth to set
     */
    public abstract void setMaxDataWidth(String maxDataWidth);


    /**
     * @return the maxLinkWidth
     */
    public abstract String getMaxLinkWidth();


    /**
     * @param maxLinkWidth the maxLinkWidth to set
     */
    public abstract void setMaxLinkWidth(String maxLinkWidth);


    /**
     * @return the numPhysicalPins
     */
    public abstract String getNumPhysicalPins();


    /**
     * @param numPhysicalPins the numPhysicalPins to set
     */
    public abstract void setNumPhysicalPins(String numPhysicalPins);


    /**
     * @return the number
     */
    public abstract String getNumber();


    /**
     * @param number the number to set
     */
    public abstract void setNumber(String number);


    /**
     * @return the openSwitch
     */
    public abstract String getOpenSwitch();


    /**
     * @param openSwitch the openSwitch to set
     */
    public abstract void setOpenSwitch(String openSwitch);


    /**
     * @return the otherTypeDescription
     */
    public abstract String getOtherTypeDescription();


    /**
     * @param otherTypeDescription the otherTypeDescription to set
     */
    public abstract void setOtherTypeDescription(String otherTypeDescription);


    /**
     * @return the powered
     */
    public abstract String getPowered();


    /**
     * @param powered the powered to set
     */
    public abstract void setPowered(String powered);


    /**
     * @return the purposeDescription
     */
    public abstract String getPurposeDescription();


    /**
     * @param purposeDescription the purposeDescription to set
     */
    public abstract void setPurposeDescription(String purposeDescription);


    /**
     * @return the vendorCompatibilityStrings
     */
    public abstract String getVendorCompatibilityStrings();


    /**
     * @param vendorCompatibilityStrings the vendorCompatibilityStrings to set
     */
    public abstract void setVendorCompatibilityStrings(String vendorCompatibilityStrings);


    /**
     * @return the specialPurpose
     */
    public abstract String getSpecialPurpose();


    /**
     * @param specialPurpose the specialPurpose to set
     */
    public abstract void setSpecialPurpose(String specialPurpose);


    /**
     * @return the supportsHotPlug
     */
    public abstract String getSupportsHotPlug();


    /**
     * @param supportsHotPlug the supportsHotPlug to set
     */
    public abstract void setSupportsHotPlug(String supportsHotPlug);


    /**
     * @return the thermalRating
     */
    public abstract String getThermalRating();


    /**
     * @param thermalRating the thermalRating to set
     */
    public abstract void setThermalRating(String thermalRating);


    /**
     * @return the operationaStatus
     */
    public abstract String getOperationalStatus();


    /**
     * @param operationalStatus the operationalStatus to set
     */
    public abstract void setOperationalStatus(String operationalStatus);


    /**
     * @return the statusDescriptions
     */
    public abstract String getStatusDescriptions();


    /**
     * @param statusDescriptions the statusDescriptions to set
     */
    public abstract void setStatusDescriptions(String statusDescriptions);


    /**
     * @return the connectorElectricalCharacteristics
     */
    public abstract String getConnectorElectricalCharacteristics();


    /**
     * @param connectorElectricalCharacteristics the connectorElectricalCharacteristics to set
     */
    public abstract void setConnectorElectricalCharacteristics(String connectorElectricalCharacteristics);


    /**
     * @return the otherElectricalCharacteristics
     */
    public abstract String getOtherElectricalCharacteristics();


    /**
     * @param otherElectricalCharacteristics the otherElectricalCharacteristics to set
     */
    public abstract void setOtherElectricalCharacteristics(String otherElectricalCharacteristics);


    /**
     * @return the connectorType
     */
    public abstract String getConnectorType();


    /**
     * @param connectorType the connectorType to set
     */
    public abstract void setConnectorType(String connectorType);


    /**
     * @return the vccMixedVoltageSupport
     */
    public abstract String getVccMixedVoltageSupport();


    /**
     * @param vccMixedVoltageSupport the vccMixedVoltageSupport to set
     */
    public abstract void setVccMixedVoltageSupport(String vccMixedVoltageSupport);


    /**
     * @return the vppMixedVoltageSupport
     */
    public abstract String getVppMixedVoltageSupport();


    /**
     * @param vppMixedVoltageSupport the vppMixedVoltageSupport to set
     */
    public abstract void setVppMixedVoltageSupport(String vppMixedVoltageSupport);

}