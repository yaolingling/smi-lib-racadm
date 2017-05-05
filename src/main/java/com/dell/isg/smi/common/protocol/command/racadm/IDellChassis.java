/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

public interface IDellChassis extends IBasePhysicalElement {

    /**
     * @return the audibleAlarm
     */
    public abstract String getAudibleAlarm();


    /**
     * @param audibleAlarm the audibleAlarm to set
     */
    public abstract void setAudibleAlarm(String audibleAlarm);


    /**
     * @return the breachDescription
     */
    public abstract String getBreachDescription();


    /**
     * @param breachDescription the breachDescription to set
     */
    public abstract void setBreachDescription(String breachDescription);


    /**
     * @return the cableManagementStrategy
     */
    public abstract String getCableManagementStrategy();


    /**
     * @param cableManagementStrategy the cableManagementStrategy to set
     */
    public abstract void setCableManagementStrategy(String cableManagementStrategy);


    /**
     * @return the chassisTypeDescription
     */
    public abstract String getChassisTypeDescription();


    /**
     * @param chassisTypeDescription the chassisTypeDescription to set
     */
    public abstract void setChassisTypeDescription(String chassisTypeDescription);


    /**
     * @return the chassisPackageType
     */
    public abstract String getChassisPackageType();


    /**
     * @param chassisPackageType the chassisPackageType to set
     */
    public abstract void setChassisPackageType(String chassisPackageType);


    /**
     * @return the currentRequiredOrProduced
     */
    public abstract String getCurrentRequiredOrProduced();


    /**
     * @param currentRequiredOrProduced the currentRequiredOrProduced to set
     */
    public abstract void setCurrentRequiredOrProduced(String currentRequiredOrProduced);


    /**
     * @return the heatGeneration
     */
    public abstract String getHeatGeneration();


    /**
     * @param heatGeneration the heatGeneration to set
     */
    public abstract void setHeatGeneration(String heatGeneration);


    /**
     * @return the inputCurrentType
     */
    public abstract String getInputCurrentType();


    /**
     * @param inputCurrentType the inputCurrentType to set
     */
    public abstract void setInputCurrentType(String inputCurrentType);


    /**
     * @return the inputVoltage
     */
    public abstract String getInputVoltage();


    /**
     * @param inputVoltage the inputVoltage to set
     */
    public abstract void setInputVoltage(String inputVoltage);


    /**
     * @return the isLocked
     */
    public abstract String getIsLocked();


    /**
     * @param isLocked the isLocked to set
     */
    public abstract void setIsLocked(String isLocked);


    /**
     * @return the lockPresent
     */
    public abstract String getLockPresent();


    /**
     * @param lockPresent the lockPresent to set
     */
    public abstract void setLockPresent(String lockPresent);


    /**
     * @return the multipleSystemSupport
     */
    public abstract String getMultipleSystemSupport();


    /**
     * @param multipleSystemSupport the multipleSystemSupport to set
     */
    public abstract void setMultipleSystemSupport(String multipleSystemSupport);


    /**
     * @return the numberOfPowerCords
     */
    public abstract String getNumberOfPowerCords();


    /**
     * @param numberOfPowerCords the numberOfPowerCords to set
     */
    public abstract void setNumberOfPowerCords(String numberOfPowerCords);


    /**
     * @return the otherInputCurrentType
     */
    public abstract String getOtherInputCurrentType();


    /**
     * @param otherInputCurrentType the otherInputCurrentType to set
     */
    public abstract void setOtherInputCurrentType(String otherInputCurrentType);


    /**
     * @return the rackMountable
     */
    public abstract String getRackMountable();


    /**
     * @param rackMountable the rackMountable to set
     */
    public abstract void setRackMountable(String rackMountable);


    /**
     * @return the securityBreach
     */
    public abstract String getSecurityBreach();


    /**
     * @param securityBreach the securityBreach to set
     */
    public abstract void setSecurityBreach(String securityBreach);


    /**
     * @return the vendorCompatibilityStrings
     */
    public abstract String getVendorCompatibilityStrings();


    /**
     * @param vendorCompatibilityStrings the vendorCompatibilityStrings to set
     */
    public abstract void setVendorCompatibilityStrings(String vendorCompatibilityStrings);


    /**
     * @return the visibleAlarm
     */
    public abstract String getVisibleAlarm();


    /**
     * @param visibleAlarm the visibleAlarm to set
     */
    public abstract void setVisibleAlarm(String visibleAlarm);

}