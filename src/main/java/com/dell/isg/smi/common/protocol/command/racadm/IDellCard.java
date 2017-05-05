/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

public interface IDellCard extends IBasePhysicalElement {

    /**
     * @return the hostingBoard
     */
    public abstract String getHostingBoard();


    /**
     * @param hostingBoard the hostingBoard to set
     */
    public abstract void setHostingBoard(String hostingBoard);


    /**
     * @return the requirementsDescription
     */
    public abstract String getRequirementsDescription();


    /**
     * @param requirementsDescription the requirementsDescription to set
     */
    public abstract void setRequirementsDescription(String requirementsDescription);


    /**
     * @return the requiresDaughterBoard
     */
    public abstract String getRequiresDaughterBoard();


    /**
     * @param requiresDaughterBoard the requiresDaughterBoard to set
     */
    public abstract void setRequiresDaughterBoard(String requiresDaughterBoard);


    /**
     * @return the slotLayout
     */
    public abstract String getSlotLayout();


    /**
     * @param slotLayout the slotLayout to set
     */
    public abstract void setSlotLayout(String slotLayout);


    /**
     * @return the specialRequirements
     */
    public abstract String getSpecialRequirements();


    /**
     * @param specialRequirements the specialRequirements to set
     */
    public abstract void setSpecialRequirements(String specialRequirements);

}