/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

public interface IDellPhysicalPackage extends IBasePhysicalElement {

    /**
     * @return the vendorCompatibilityStrings
     */
    public abstract String getVendorCompatibilityStrings();


    /**
     * @param vendorCompatibilityStrings the vendorCompatibilityStrings to set
     */
    public abstract void setVendorCompatibilityStrings(String vendorCompatibilityStrings);


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
     * @return the statusDescriptions
     */
    public abstract void setStatusDescriptions(String statusDescriptions);

}