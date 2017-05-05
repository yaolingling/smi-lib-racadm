/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

public class IOModuleEntity {
    private String number;
    private String name;
    private String role;
    private String slot;
    private String fabric;
    private String serviceTag;
    private String ipAddress;
    private String macAddress;
    private String gateway;
    private String subnetMask;
    private boolean present;
    private String hardwareVersion;
    private String firmwareVersion;
    private boolean dhcpEnbaled;
    private String powerStatus;


    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getSlot() {
        return slot;
    }


    public void setSlot(String slot) {
        this.slot = slot;
    }


    public String getFabric() {
        return fabric;
    }


    public void setFabric(String fabric) {
        this.fabric = fabric;
    }


    public String getServiceTag() {
        return serviceTag;
    }


    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }


    public String getIpAddress() {
        return ipAddress;
    }


    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }


    public String getMacAddress() {
        return macAddress;
    }


    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }


    public String getGateway() {
        return gateway;
    }


    public void setGateway(String gatway) {
        this.gateway = gatway;
    }


    public String getSubnetMask() {
        return subnetMask;
    }


    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }


    public boolean isPresent() {
        return present;
    }


    public void setPresent(boolean presence) {
        this.present = presence;
    }


    public String getHardwareVersion() {
        return hardwareVersion;
    }


    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }


    public String getFirmwareVersion() {
        return firmwareVersion;
    }


    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }


    public boolean isDhcpEnbaled() {
        return dhcpEnbaled;
    }


    public void setDhcpEnbaled(boolean dhcpEnbaled) {
        this.dhcpEnbaled = dhcpEnbaled;
    }


    public String getPowerStatus() {
        return powerStatus;
    }


    public void setPowerStatus(String powerStatus) {
        this.powerStatus = powerStatus;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (dhcpEnbaled ? 1231 : 1237);
        result = prime * result + ((fabric == null) ? 0 : fabric.hashCode());
        result = prime * result + ((firmwareVersion == null) ? 0 : firmwareVersion.hashCode());
        result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
        result = prime * result + ((hardwareVersion == null) ? 0 : hardwareVersion.hashCode());
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = prime * result + ((macAddress == null) ? 0 : macAddress.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((powerStatus == null) ? 0 : powerStatus.hashCode());
        result = prime * result + (present ? 1231 : 1237);
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((serviceTag == null) ? 0 : serviceTag.hashCode());
        result = prime * result + ((slot == null) ? 0 : slot.hashCode());
        result = prime * result + ((subnetMask == null) ? 0 : subnetMask.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IOModuleEntity other = (IOModuleEntity) obj;
        if (dhcpEnbaled != other.dhcpEnbaled)
            return false;
        if (fabric == null) {
            if (other.fabric != null)
                return false;
        } else if (!fabric.equals(other.fabric))
            return false;
        if (firmwareVersion == null) {
            if (other.firmwareVersion != null)
                return false;
        } else if (!firmwareVersion.equals(other.firmwareVersion))
            return false;
        if (gateway == null) {
            if (other.gateway != null)
                return false;
        } else if (!gateway.equals(other.gateway))
            return false;
        if (hardwareVersion == null) {
            if (other.hardwareVersion != null)
                return false;
        } else if (!hardwareVersion.equals(other.hardwareVersion))
            return false;
        if (ipAddress == null) {
            if (other.ipAddress != null)
                return false;
        } else if (!ipAddress.equals(other.ipAddress))
            return false;
        if (macAddress == null) {
            if (other.macAddress != null)
                return false;
        } else if (!macAddress.equals(other.macAddress))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (powerStatus == null) {
            if (other.powerStatus != null)
                return false;
        } else if (!powerStatus.equals(other.powerStatus))
            return false;
        if (present != other.present)
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (serviceTag == null) {
            if (other.serviceTag != null)
                return false;
        } else if (!serviceTag.equals(other.serviceTag))
            return false;
        if (slot == null) {
            if (other.slot != null)
                return false;
        } else if (!slot.equals(other.slot))
            return false;
        if (subnetMask == null) {
            if (other.subnetMask != null)
                return false;
        } else if (!subnetMask.equals(other.subnetMask))
            return false;
        return true;
    }

}
