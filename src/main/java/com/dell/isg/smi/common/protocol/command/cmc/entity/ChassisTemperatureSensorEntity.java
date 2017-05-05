/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

public class ChassisTemperatureSensorEntity {
    protected String sensorUnits;
    protected String sensorName;
    protected String status;
    protected String reading;
    protected String lw;
    protected String lc;
    protected String uw;
    protected String uc;
    protected String num;


    public String getSensorUnits() {
        return sensorUnits;
    }


    public void setSensorUnits(String sensorUnits) {
        this.sensorUnits = sensorUnits;
    }


    public String getSensorName() {
        return sensorName;
    }


    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
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


    public String getLw() {
        return lw;
    }


    public void setLw(String lw) {
        this.lw = lw;
    }


    public String getLc() {
        return lc;
    }


    public void setLc(String lc) {
        this.lc = lc;
    }


    public String getUw() {
        return uw;
    }


    public void setUw(String uw) {
        this.uw = uw;
    }


    public String getUc() {
        return uc;
    }


    public void setUc(String uc) {
        this.uc = uc;
    }


    public String getNum() {
        return num;
    }


    public void setNum(String num) {
        this.num = num;
    }
}
