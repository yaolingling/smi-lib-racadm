/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

public interface IBasePhysicalElement extends IBaseElement {

    /**
     * @return the packageType
     */
    public abstract String getPackageType();


    /**
     * @param packageType the packageType to set
     */
    public abstract void setPackageType(String packageType);


    /**
     * @return the otherPackageType
     */
    public abstract String getOtherPackageType();


    /**
     * @param otherPackageType the otherPackageType to set
     */
    public abstract void setOtherPackageType(String otherPackageType);


    /**
     * @return the depth
     */
    public abstract String getDepth();


    /**
     * @param depth the depth to set
     */
    public abstract void setDepth(String depth);


    /**
     * @return the hotSwappable
     */
    public abstract String getHotSwappable();


    /**
     * @param hotSwappable the hotSwappable to set
     */
    public abstract void setHotSwappable(String hotSwappable);


    /**
     * @return the height
     */
    public abstract String getHeight();


    /**
     * @param height the height to set
     */
    public abstract void setHeight(String height);


    /**
     * @return the removable
     */
    public abstract String getRemovable();


    /**
     * @param removable the removable to set
     */
    public abstract void setRemovable(String removable);


    /**
     * @return the removalConditions
     */
    public abstract String getRemovalConditions();


    /**
     * @param removalConditions the removalConditions to set
     */
    public abstract void setRemovalConditions(String removalConditions);


    /**
     * @return the replaceable
     */
    public abstract String getReplaceable();


    /**
     * @param replaceable the replaceable to set
     */
    public abstract void setReplaceable(String replaceable);


    /**
     * @return the weight
     */
    public abstract String getWeight();


    /**
     * @param weight the weight to set
     */
    public abstract void setWeight(String weight);


    /**
     * @return the width
     */
    public abstract String getWidth();


    /**
     * @param width the width to set
     */
    public abstract void setWidth(String width);

}