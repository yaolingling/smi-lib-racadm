/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

public interface IBaseElement {

    /**
     * @return the canBeFRUed
     */
    public abstract String getCanBeFRUed();


    /**
     * @param canBeFRUed the canBeFRUed to set
     */
    public abstract void setCanBeFRUed(String canBeFRUed);


    /**
     * @return the caption
     */
    public abstract String getCaption();


    /**
     * @param caption the caption to set
     */
    public abstract void setCaption(String caption);


    /**
     * @return the classId
     */
    public abstract String getClassId();


    /**
     * @param classId the classId to set
     */
    public abstract void setClassId(String classId);


    /**
     * @return the communicationStatus
     */
    public abstract String getCommunicationStatus();


    /**
     * @param communicationStatus the communicationStatus to set
     */
    public abstract void setCommunicationStatus(String communicationStatus);


    /**
     * @return the creationClassName
     */
    public abstract String getCreationClassName();


    /**
     * @param creationClassName the creationClassName to set
     */
    public abstract void setCreationClassName(String creationClassName);


    /**
     * @return the description
     */
    public abstract String getDescription();


    /**
     * @param description the description to set
     */
    public abstract void setDescription(String description);


    /**
     * @return the detailedStatus
     */
    public abstract String getDetailedStatus();


    /**
     * @param detailedStatus the detailedStatus to set
     */
    public abstract void setDetailedStatus(String detailedStatus);


    /**
     * @return the elementName
     */
    public abstract String getElementName();


    /**
     * @param elementName the elementName to set
     */
    public abstract void setElementName(String elementName);


    /**
     * @return the generation
     */
    public abstract String getGeneration();


    /**
     * @param generation the generation to set
     */
    public abstract void setGeneration(String generation);


    /**
     * @return the healthState
     */
    public abstract String getHealthState();


    /**
     * @param healthState the healthState to set
     */
    public abstract void setHealthState(String healthState);


    /**
     * @return the installDate
     */
    public abstract String getInstallDate();


    /**
     * @param installDate the installDate to set
     */
    public abstract void setInstallDate(String installDate);


    /**
     * @return the instanceId
     */
    public abstract String getInstanceId();


    /**
     * @param instanceId the instanceId to set
     */
    public abstract void setInstanceId(String instanceId);


    /**
     * @return the manufactureDate
     */
    public abstract String getManufactureDate();


    /**
     * @param manufactureDate the manufactureDate to set
     */
    public abstract void setManufactureDate(String manufactureDate);


    /**
     * @return the manufacturer
     */
    public abstract String getManufacturer();


    /**
     * @param manufacturer the manufacturer to set
     */
    public abstract void setManufacturer(String manufacturer);


    /**
     * @return the model
     */
    public abstract String getModel();


    /**
     * @param model the model to set
     */
    public abstract void setModel(String model);


    /**
     * @return the name
     */
    public abstract String getName();


    /**
     * @param name the name to set
     */
    public abstract void setName(String name);


    /**
     * @return the operatingStatus
     */
    public abstract String getOperatingStatus();


    /**
     * @param operatingStatus the operatingStatus to set
     */
    public abstract void setOperatingStatus(String operatingStatus);


    /**
     * @return the otherIdentifyingInfo
     */
    public abstract String getOtherIdentifyingInfo();


    /**
     * @param otherIdentifyingInfo the otherIdentifyingInfo to set
     */
    public abstract void setOtherIdentifyingInfo(String otherIdentifyingInfo);


    /**
     * @return the partNumber
     */
    public abstract String getPartNumber();


    /**
     * @param partNumber the partNumber to set
     */
    public abstract void setPartNumber(String partNumber);


    /**
     * @return the poweredOn
     */
    public abstract String getPoweredOn();


    /**
     * @param poweredOn the poweredOn to set
     */
    public abstract void setPoweredOn(String poweredOn);


    /**
     * @return the primaryStatus
     */
    public abstract String getPrimaryStatus();


    /**
     * @param primaryStatus the primaryStatus to set
     */
    public abstract void setPrimaryStatus(String primaryStatus);


    /**
     * @return the sku
     */
    public abstract String getSku();


    /**
     * @param sku the sku to set
     */
    public abstract void setSku(String sku);


    /**
     * @return the serialNumber
     */
    public abstract String getSerialNumber();


    /**
     * @param serialNumber the serialNumber to set
     */
    public abstract void setSerialNumber(String serialNumber);


    /**
     * @return the status
     */
    public abstract String getStatus();


    /**
     * @param status the status to set
     */
    public abstract void setStatus(String status);


    /**
     * @return the tag
     */
    public abstract String getTag();


    /**
     * @param tag the tag to set
     */
    public abstract void setTag(String tag);


    /**
     * @return the userTracking
     */
    public abstract String getUserTracking();


    /**
     * @param userTracking the userTracking to set
     */
    public abstract void setUserTracking(String userTracking);


    /**
     * @return the vendorEquipmentType
     */
    public abstract String getVendorEquipmentType();


    /**
     * @param vendorEquipmentType the vendorEquipmentType to set
     */
    public abstract void setVendorEquipmentType(String vendorEquipmentType);


    /**
     * @return the version
     */
    public abstract String getVersion();


    /**
     * @param version the version to set
     */
    public abstract void setVersion(String version);

}