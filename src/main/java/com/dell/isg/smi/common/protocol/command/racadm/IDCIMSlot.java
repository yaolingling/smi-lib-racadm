/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

public interface IDCIMSlot extends IBaseElement {

    /**
     * @return the assignedBladeFQDD
     */
    public abstract String getAssignedBladeFQDD();


    /**
     * @param assignedBladeFQDD the assignedBladeFQDD to set
     */
    public abstract void setAssignedBladeFQDD(String assignedBladeFQDD);


    /**
     * @return the assignedBladeSlotFQDD
     */
    public abstract String getAssignedBladeSlotFQDD();


    /**
     * @param assignedBladeSlotFQDD the assignedBladeSlotFQDD to set
     */
    public abstract void setAssignedBladeSlotFQDD(String assignedBladeSlotFQDD);


    /**
     * @return the assignedMezzFQDD
     */
    public abstract String getAssignedMezzFQDD();


    /**
     * @param assignedMezzFQDD the assignedMezzFQDD to set
     */
    public abstract void setAssignedMezzFQDD(String assignedMezzFQDD);


    /**
     * @return the cardModule
     */
    public abstract String getCardModule();


    /**
     * @param cardModule the cardModule to set
     */
    public abstract void setCardModule(String cardModule);


    /**
     * @return the currentAssignState
     */
    public abstract String getCurrentAssignState();


    /**
     * @param currentAssignState the currentAssignState to set
     */
    public abstract void setCurrentAssignState(String currentAssignState);


    /**
     * @return the deviceFQDD
     */
    public abstract String getDeviceFQDD();


    /**
     * @param deviceFQDD the deviceFQDD to set
     */
    public abstract void setDeviceFQDD(String deviceFQDD);


    /**
     * @return the emptySlot
     */
    public abstract String getEmptySlot();


    /**
     * @param emptySlot the emptySlot to set
     */
    public abstract void setEmptySlot(String emptySlot);


    /**
     * @return the fqdd
     */
    public abstract String getFQDD();


    /**
     * @param fqdd the fqdd to set
     */
    public abstract void setFQDD(String fqdd);


    /**
     * @return the fabric
     */
    public abstract String getFabric();


    /**
     * @param fabric the fabric to set
     */
    public abstract void setFabric(String fabric);


    /**
     * @return the height
     */
    public abstract String getHeight();


    /**
     * @param height the height to set
     */
    public abstract void setHeight(String height);


    /**
     * @return the numberDescription
     */
    public abstract String getNumberDescription();


    /**
     * @param numberDescription the numberDescription to set
     */
    public abstract void setNumberDescription(String numberDescription);


    /**
     * @return the pendingAction
     */
    public abstract String getPendingAction();


    /**
     * @param pendingAction the pendingAction to set
     */
    public abstract void setPendingAction(String pendingAction);


    /**
     * @return the powerStateStatus
     */
    public abstract String getPowerStateStatus();


    /**
     * @param powerStateStatus the powerStateStatus to set
     */
    public abstract void setPowerStateStatus(String powerStateStatus);


    /**
     * @return the slotPowerCap
     */
    public abstract String getSlotPowerCap();


    /**
     * @param slotPowerCap the slotPowerCap to set
     */
    public abstract void setSlotPowerCap(String slotPowerCap);

}