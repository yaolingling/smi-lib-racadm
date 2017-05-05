/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

public class BladeServerViewEntity {
    public static enum BLADE_TYPE {
        FULL, HALF, QUARTER
    }

    public static final int GENERATION_12G = 17;

    private String sAssetTag;
    private String sBiosVersion;
    private String sIDRACVersion;
    private String sLCVersion;
    private boolean bDHCPEnabled = false;
    private boolean bIPv4Enabled = true;
    private String sDNSName;
    private String sHostname;
    private String sIPv4Address;
    private String sIPv4SubnetMask;
    private String sIPv4Gateway;
    private int iGeneration;
    private String sModel;
    private String sSlotName;
    private String sSubSlot = null;
    private String sServiceTag;
    private int iPowerState;
    private int iPrimaryStatus;
    private int iModelNumber;
    private int iSlotNumber;
    private String sFQDD;

    private BLADE_TYPE type = BLADE_TYPE.HALF;


    public BLADE_TYPE getType() {
        return type;
    }


    public void setType(BLADE_TYPE type) {
        this.type = type;
    }


    public String getAssetTag() {
        return sAssetTag;
    }


    public void setAssetTag(String a) {
        sAssetTag = a;
    }


    public String getBiosVersion() {
        return sBiosVersion;
    }


    public void setBiosVersion(String a) {
        sBiosVersion = a;
    }


    // public String getLCVersion() { return sLCVersion; }
    // public void setLCVersion(String a) { sLCVersion = a; }

    public String getIDracVersion() {
        return sIDRACVersion;
    }


    public void setIDracVersion(String a) {
        sIDRACVersion = a;
    }


    public boolean isDHCPEnabled() {
        return bDHCPEnabled;
    }


    public void setDHCPEnabled(boolean a) {
        bDHCPEnabled = a;
    }


    public boolean isIPv4Enabled() {
        return bIPv4Enabled;
    }


    public void setIPv4Enabled(boolean a) {
        bIPv4Enabled = a;
    }


    public String getDNSName() {
        return sDNSName;
    }


    public void setDNSName(String a) {
        sDNSName = a;
    }


    public String getHostName() {
        return sHostname;
    }


    public void setHostName(String a) {
        sHostname = a;
    }


    public String getIPv4Address() {
        return sIPv4Address;
    }


    public void setIPv4Addrress(String a) {
        sIPv4Address = a;
    }


    public String getIPv4SubnetMask() {
        return sIPv4SubnetMask;
    }


    public void setIPv4SubnetMask(String a) {
        sIPv4SubnetMask = a;
    }


    public String getIPv4Gateway() {
        return sIPv4Gateway;
    }


    public void setIPv4Gateway(String a) {
        sIPv4Gateway = a;
    }


    public int getGeneration() {
        return iGeneration;
    }


    public void setGeneration(int a) {
        iGeneration = a;
    }


    public String getModel() {
        return sModel;
    }


    public void setModel(String a) {
        sModel = a;
    }


    public String getSlotName() {
        return sSlotName;
    }


    public void setSlotName(String a) {
        sSlotName = a;
    }


    public String getServiceTag() {
        return sServiceTag;
    }


    public void setServiceTag(String a) {
        sServiceTag = a;
    }


    public int getPowerState() {
        return iPowerState;
    }


    public void setPowerState(int a) {
        iPowerState = a;
    }


    public int getPrimaryStatus() {
        return iPrimaryStatus;
    }


    public void setPrimaryStatus(int a) {
        iPrimaryStatus = a;
    }


    public int getModelNumber() {
        return iModelNumber;
    }


    public void setModelNumber(int a) {
        iModelNumber = a;
    }


    public int getSlotNumber() {
        return iSlotNumber;
    }


    public void setSlotNumber(int a) {
        iSlotNumber = a;
    }


    public String getFQDD() {
        return sFQDD;
    }


    public void setFQDD(String a) {
        sFQDD = a;
    }


    public String getSubSlot() {
        return sSubSlot;
    }


    public void setSubSlot(String a) {
        sSubSlot = a;
    }


    public String toString() {
        return (sFQDD + " " + sModel + " " + sServiceTag + " " + sSlotName + " " + iGeneration + " " + type + " " + iPowerState + " " + iPrimaryStatus + " " + sLCVersion);
    }

}
