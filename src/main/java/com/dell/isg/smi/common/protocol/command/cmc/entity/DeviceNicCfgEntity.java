/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

import java.io.Serializable;

public class DeviceNicCfgEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String ipv4Address = "";
    private String ipv4SubnetMask = "";
    private String ipv4Gateway = "";
    private boolean bDHCPEnabled = false;
    private boolean bIPv4Enabled = true;


    public String getIpv4Address() {
        return ipv4Address;
    }


    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }


    public String getIpv4SubnetMask() {
        return ipv4SubnetMask;
    }


    public void setIpv4SubnetMask(String ipv4SubnetMask) {
        this.ipv4SubnetMask = ipv4SubnetMask;
    }


    public String getIpv4Gateway() {
        return ipv4Gateway;
    }


    public void setIpv4Gateway(String ipv4Gateway) {
        this.ipv4Gateway = ipv4Gateway;
    }


    public boolean isbDHCPEnabled() {
        return bDHCPEnabled;
    }


    public void setbDHCPEnabled(boolean bDHCPEnabled) {
        this.bDHCPEnabled = bDHCPEnabled;
    }


    public boolean isbIPv4Enabled() {
        return bIPv4Enabled;
    }


    public void setbIPv4Enabled(boolean bIPv4Enabled) {
        this.bIPv4Enabled = bIPv4Enabled;
    }


    public String toString() {
        return (" " + this.bDHCPEnabled + " " + this.ipv4Address + " " + this.ipv4SubnetMask + " " + this.ipv4Gateway);
    }

}
