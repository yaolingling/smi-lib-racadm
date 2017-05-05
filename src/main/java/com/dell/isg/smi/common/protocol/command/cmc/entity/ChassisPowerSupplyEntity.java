/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

public class ChassisPowerSupplyEntity {
    private String name;
    private String present;
    private String powerState;
    private String model;
    private String inputCurrent;
    private String inputVolts;
    private String outputRatedPower;
    private String health;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPresent() {
        return present;
    }


    public void setPresent(String present) {
        this.present = present;
    }


    public String getPowerState() {
        return powerState;
    }


    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }


    public String getModel() {
        return model;
    }


    public void setModel(String model) {
        this.model = model;
    }


    public String getInputCurrent() {
        return inputCurrent;
    }


    public void setInputCurrent(String inputCurrent) {
        this.inputCurrent = inputCurrent;
    }


    public String getInputVolts() {
        return inputVolts;
    }


    public void setInputVolts(String inputVolts) {
        this.inputVolts = inputVolts;
    }


    public String getOutputRatedPower() {
        return outputRatedPower;
    }


    public void setOutputRatedPower(String outputRatedPower) {
        this.outputRatedPower = outputRatedPower;
    }


    public String getHealth() {
        return health;
    }


    public void setHealth(String health) {
        this.health = health;
    }
}
