/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

import java.io.Serializable;
import java.util.Date;

public class ChassisCMCViewEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // CMC Information
    private Date cmcDateTime = null;
    private String primaryCMCVersion;
    private String standByCMCVersion;
    private Date lastFirmwareUpdateTime;
    private String primaryCMCLocation;
    private String hardwareVersion;
    // CMC Network Information
    private boolean nicEnabled;
    private String macAddress;
    private boolean registerDNSNameForCMC;
    private String dnsCMCName;
    private String currentDNSDomain;
    private String vLanId;
    private String vLanPriority;
    private boolean vLanEnabled;
    // IPV4 Information
    private boolean ipv4Enabled;
    private String currentIpAddress;
    private String currentIpGateway;
    private String currentIpNetmask;
    private boolean dhcpEnabled;
    private String currentDNSServer1;
    private String currentDNSServer2;
    private boolean dnsServersFromDhcp;
    // IPV6 Information
    private boolean ipv6Enabled;
    private boolean autoConfigurationEnabled;
    private String linkLocalAddress;
    private String currentIpv6Address;
    private String currentIpv6Gateway;
    private String currentIpv6DNSServer1;
    private String currentUIpv6DNSServer2;
    private boolean dnsServersFromDhcpv6;
    // Chassis Information
    private String systemModel;
    private String assetTag;
    private String serviceTag;
    private String chassisName;
    private String chassisLocation;
    private String chassisMidPlaneVersion;
    private String powerStatus;


    public Date getCmcDateTime() {
        return cmcDateTime;
    }


    public void setCmcDateTime(Date cmcDateTime) {
        this.cmcDateTime = cmcDateTime;
    }


    public String getPrimaryCMCVersion() {
        return primaryCMCVersion;
    }


    public void setPrimaryCMCVersion(String primaryCMCVersion) {
        this.primaryCMCVersion = primaryCMCVersion;
    }


    public String getStandByCMCVersion() {
        return standByCMCVersion;
    }


    public void setStandByCMCVersion(String standByCMCVersion) {
        this.standByCMCVersion = standByCMCVersion;
    }


    public Date getLastFirmwareUpdateTime() {
        return lastFirmwareUpdateTime;
    }


    public void setLastFirmwareUpdateTime(Date lastFirmwareUpdateTime) {
        this.lastFirmwareUpdateTime = lastFirmwareUpdateTime;
    }


    public String getPrimaryCMCLocation() {
        return primaryCMCLocation;
    }


    public void setPrimaryCMCLocation(String primaryCMCLocation) {
        this.primaryCMCLocation = primaryCMCLocation;
    }


    public String getHardwareVersion() {
        return hardwareVersion;
    }


    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }


    public boolean isNicEnabled() {
        return nicEnabled;
    }


    public void setNicEnabled(boolean nicEnabled) {
        this.nicEnabled = nicEnabled;
    }


    public String getMacAddress() {
        return macAddress;
    }


    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }


    public boolean isRegisterDNSNameForCMC() {
        return registerDNSNameForCMC;
    }


    public void setRegisterDNSNameForCMC(boolean registerDNSNameForCMC) {
        this.registerDNSNameForCMC = registerDNSNameForCMC;
    }


    public String getDnsCMCName() {
        return dnsCMCName;
    }


    public void setDnsCMCName(String dnsCMCName) {
        this.dnsCMCName = dnsCMCName;
    }


    public String getCurrentDNSDomain() {
        return currentDNSDomain;
    }


    public void setCurrentDNSDomain(String currentDNSDomain) {
        this.currentDNSDomain = currentDNSDomain;
    }


    public String getvLanId() {
        return vLanId;
    }


    public void setvLanId(String vLanId) {
        this.vLanId = vLanId;
    }


    public String getvLanPriority() {
        return vLanPriority;
    }


    public void setvLanPriority(String vLanPriority) {
        this.vLanPriority = vLanPriority;
    }


    public boolean isvLanEnabled() {
        return vLanEnabled;
    }


    public void setvLanEnabled(boolean vLanEnabled) {
        this.vLanEnabled = vLanEnabled;
    }


    public boolean isIpv4Enabled() {
        return ipv4Enabled;
    }


    public void setIpv4Enabled(boolean ipv4Enabled) {
        this.ipv4Enabled = ipv4Enabled;
    }


    public String getCurrentIpAddress() {
        return currentIpAddress;
    }


    public void setCurrentIpAddress(String currentIpAddress) {
        this.currentIpAddress = currentIpAddress;
    }


    public String getCurrentIpGateway() {
        return currentIpGateway;
    }


    public void setCurrentIpGateway(String currentIpGateway) {
        this.currentIpGateway = currentIpGateway;
    }


    public String getCurrentIpNetmask() {
        return currentIpNetmask;
    }


    public void setCurrentIpNetmask(String currentIpNetmask) {
        this.currentIpNetmask = currentIpNetmask;
    }


    public boolean isDhcpEnabled() {
        return dhcpEnabled;
    }


    public void setDhcpEnabled(boolean dhcpEnabled) {
        this.dhcpEnabled = dhcpEnabled;
    }


    public String getCurrentDNSServer1() {
        return currentDNSServer1;
    }


    public void setCurrentDNSServer1(String currentDNSServer1) {
        this.currentDNSServer1 = currentDNSServer1;
    }


    public String getCurrentDNSServer2() {
        return currentDNSServer2;
    }


    public void setCurrentDNSServer2(String currentDNSServer2) {
        this.currentDNSServer2 = currentDNSServer2;
    }


    public boolean isDnsServersFromDhcp() {
        return dnsServersFromDhcp;
    }


    public void setDnsServersFromDhcp(boolean dnsServersFromDhcp) {
        this.dnsServersFromDhcp = dnsServersFromDhcp;
    }


    public boolean isIpv6Enabled() {
        return ipv6Enabled;
    }


    public void setIpv6Enabled(boolean ipv6Enabled) {
        this.ipv6Enabled = ipv6Enabled;
    }


    public boolean isAutoConfigurationEnabled() {
        return autoConfigurationEnabled;
    }


    public void setAutoConfigurationEnabled(boolean autoConfigurationEnabled) {
        this.autoConfigurationEnabled = autoConfigurationEnabled;
    }


    public String getLinkLocalAddress() {
        return linkLocalAddress;
    }


    public void setLinkLocalAddress(String linkLocalAddress) {
        this.linkLocalAddress = linkLocalAddress;
    }


    public String getCurrentIpv6Address() {
        return currentIpv6Address;
    }


    public void setCurrentIpv6Address(String currentIpv6Address) {
        this.currentIpv6Address = currentIpv6Address;
    }


    public String getCurrentIpv6Gateway() {
        return currentIpv6Gateway;
    }


    public void setCurrentIpv6Gateway(String currentIpv6Gateway) {
        this.currentIpv6Gateway = currentIpv6Gateway;
    }


    public String getCurrentIpv6DNSServer1() {
        return currentIpv6DNSServer1;
    }


    public void setCurrentIpv6DNSServer1(String currentIpv6DNSServer1) {
        this.currentIpv6DNSServer1 = currentIpv6DNSServer1;
    }


    public String getCurrentUIpv6DNSServer2() {
        return currentUIpv6DNSServer2;
    }


    public void setCurrentUIpv6DNSServer2(String currentUIpv6DNSServer2) {
        this.currentUIpv6DNSServer2 = currentUIpv6DNSServer2;
    }


    public boolean isDnsServersFromDhcpv6() {
        return dnsServersFromDhcpv6;
    }


    public void setDnsServersFromDhcpv6(boolean dnsServersFromDhcpv6) {
        this.dnsServersFromDhcpv6 = dnsServersFromDhcpv6;
    }


    public String getSystemModel() {
        return systemModel;
    }


    public void setSystemModel(String systemModel) {
        this.systemModel = systemModel;
    }


    public String getAssetTag() {
        return assetTag;
    }


    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }


    public String getServiceTag() {
        return serviceTag;
    }


    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }


    public String getChassisName() {
        return chassisName;
    }


    public void setChassisName(String chassisName) {
        this.chassisName = chassisName;
    }


    public String getChassisLocation() {
        return chassisLocation;
    }


    public void setChassisLocation(String chassisLocation) {
        this.chassisLocation = chassisLocation;
    }


    public String getChassisMidPlaneVersion() {
        return chassisMidPlaneVersion;
    }


    public void setChassisMidPlaneVersion(String chassisMidPlaneVersion) {
        this.chassisMidPlaneVersion = chassisMidPlaneVersion;
    }


    public String getPowerStatus() {
        return powerStatus;
    }


    public void setPowerStatus(String powerStatus) {
        this.powerStatus = powerStatus;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
