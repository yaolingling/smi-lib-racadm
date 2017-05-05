/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

/**
 * @author rahman.muhammad
 *
 */
public class ChassisIkvm {

    private String name;
    private String manufacturer;
    private String partnumber;
    private String firmwareVersion;
    private boolean present;
    private String health;
    private String powerStatus;
    private boolean frontPanelEnabled;
    private boolean accessToCmcEnabled;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getManufacturer() {
        return manufacturer;
    }


    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    public String getPartnumber() {
        return partnumber;
    }


    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }


    public String getFirmwareVersion() {
        return firmwareVersion;
    }


    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }


    public boolean isPresent() {
        return present;
    }


    public void setPresent(boolean present) {
        this.present = present;
    }


    public String getHealth() {
        return health;
    }


    public void setHealth(String health) {
        this.health = health;
    }


    public String getPowerStatus() {
        return powerStatus;
    }


    public void setPowerStatus(String powerStatus) {
        this.powerStatus = powerStatus;
    }


    public boolean isFrontPanelEnabled() {
        return frontPanelEnabled;
    }


    public void setFrontPanelEnabled(boolean frontPanelEnabled) {
        this.frontPanelEnabled = frontPanelEnabled;
    }


    public boolean isAccessToCmcEnabled() {
        return accessToCmcEnabled;
    }


    public void setAccessToCmcEnabled(boolean accessToCmcEnabled) {
        this.accessToCmcEnabled = accessToCmcEnabled;
    }

}