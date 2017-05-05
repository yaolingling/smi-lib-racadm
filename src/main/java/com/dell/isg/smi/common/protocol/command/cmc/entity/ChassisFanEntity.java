/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

public class ChassisFanEntity {
    private String name;
    private boolean present;
    private String powerState;
    private String status;
    private String reading;
    private String units;
    private String lc;
    private String uc;
    private String lw;
    private String uw;
    private String health;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public boolean isPresent() {
        return present;
    }


    public void setPresent(boolean present) {
        this.present = present;
    }


    public String getPowerState() {
        return powerState;
    }


    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getReading() {
        return reading;
    }


    public void setReading(String reading) {
        this.reading = reading;
    }


    public String getUnits() {
        return units;
    }


    public void setUnits(String units) {
        this.units = units;
    }


    public String getLc() {
        return lc;
    }


    public void setLc(String lc) {
        this.lc = lc;
    }


    public String getUc() {
        return uc;
    }


    public void setUc(String uc) {
        this.uc = uc;
    }


    public String getLw() {
        return lw;
    }


    public void setLw(String lw) {
        this.lw = lw;
    }


    public String getUw() {
        return uw;
    }


    public void setUw(String uw) {
        this.uw = uw;
    }


    public String getHealth() {
        return health;
    }


    public void setHealth(String health) {
        this.health = health;
    }

}
