/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.CMCIPv4Info;
import com.dell.isg.smi.common.protocol.command.chassis.entity.CMCInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.CMCNetworkInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.ChassisInfo;
import com.dell.isg.smi.common.protocol.command.cmc.entity.ChassisCMCViewEntity;
import com.dell.isg.smi.common.protocol.command.cmc.entity.RacadmCredentials;

public class EnumChassisCMCViewCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumChassisCMCViewCmd.class);
    private RacadmCredentials credentials = null;
    private String ipAddr = null;


    public EnumChassisCMCViewCmd(String ipAddr, String userName, String passwd, boolean check) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumChassisCMCViewCmd(String ipAddr - %s, String userName - %s, String passwd - %s)", ipAddr, userName, "####"));
        }

        this.credentials = new RacadmCredentials();
        this.credentials.setUserName(userName);
        this.credentials.setPassword(passwd);
        this.credentials.setCertificateCheck(check);
        this.ipAddr = ipAddr;

        logger.trace("Exiting constructor: EnumChassisCMCViewCmd()");
    }


    public ChassisCMCViewEntity execute() throws Exception {
        ChassisCMCViewEntity cmcView = new ChassisCMCViewEntity();

        EnumSysInfoCmd enumSysInfoCmd = new EnumSysInfoCmd(this.ipAddr, credentials.getUserName(), credentials.getPassword(), credentials.isCertificateCheck());

        // Fill the CMC info
        CMCInfo cmcInfo = enumSysInfoCmd.getCMCInfoEntity();
        if (null != cmcInfo) {
            cmcView.setPrimaryCMCLocation(cmcInfo.getPrimaryCMCLocation());

            String cmcDateTime = cmcInfo.getCMCDateTime();
            if (!("N/A").equalsIgnoreCase(cmcDateTime)) {
                try {
                    cmcView.setCmcDateTime(this.getDateFromString(cmcDateTime));
                } catch (Exception e) {
                }
            }

            cmcView.setPrimaryCMCVersion(cmcInfo.getPrimaryCMCVersion());
            cmcView.setStandByCMCVersion(cmcInfo.getStandbyCMCVersion());

            cmcDateTime = cmcInfo.getLastFirmwareUpdate();
            if (!("N/A").equalsIgnoreCase(cmcDateTime)) {
                try {
                    cmcView.setLastFirmwareUpdateTime(this.getDateFromString(cmcDateTime));
                } catch (Exception e) {
                }
            }

            cmcView.setHardwareVersion(cmcInfo.getHardwareVersion());

        }

        // Set the CMC Network information
        CMCNetworkInfo cmcNetworkInfo = enumSysInfoCmd.getCMCNetworkInfoEntity();
        if (null != cmcNetworkInfo) {
            String value = cmcNetworkInfo.getNICEnabled();
            if (value != null && value.equals("1")) {
                cmcView.setNicEnabled(true);
            }

            cmcView.setMacAddress(cmcNetworkInfo.getMACAddress());

            value = cmcNetworkInfo.getRegisterDNSCMCName();
            if (value != null && value.equals("1")) {
                cmcView.setRegisterDNSNameForCMC(true);
            }

            cmcView.setDnsCMCName(cmcNetworkInfo.getDNSCMCName());
            cmcView.setCurrentDNSDomain(cmcNetworkInfo.getCurrentDNSDomain());
            cmcView.setvLanId(cmcNetworkInfo.getVLANID());
            cmcView.setvLanPriority(cmcNetworkInfo.getVLANPriority());

            value = cmcNetworkInfo.getVLANEnabled();
            if (value != null && value.equals("1")) {
                cmcView.setvLanEnabled(true);
            }
        }

        // CMC IPv4 Information
        CMCIPv4Info cmcIPv4Info = enumSysInfoCmd.getCMCIPv4InfoEntity();
        if (null != cmcIPv4Info) {
            String value = cmcIPv4Info.getIPv4Enabled();
            if (value != null && value.equals("1")) {
                cmcView.setIpv4Enabled(true);
            }
            cmcView.setCurrentIpAddress(cmcIPv4Info.getCurrentIPAddress());
            cmcView.setCurrentIpGateway(cmcIPv4Info.getCurrentIPGateway());
            cmcView.setCurrentIpNetmask(cmcIPv4Info.getCurrentIPNetmask());

            value = cmcIPv4Info.getDHCPEnabled();
            if (value != null && value.equals("1")) {
                cmcView.setDhcpEnabled(true);
            }

            cmcView.setCurrentDNSServer1(cmcIPv4Info.getCurrentDNSServer1());
            cmcView.setCurrentDNSServer2(cmcIPv4Info.getCurrentDNSServer2());

            value = cmcIPv4Info.getDNSServersFromDHCP();
            if (value != null && value.equals("1")) {
                cmcView.setDnsServersFromDhcp(true);
            }

        }

        // TODO: Skipping IPv6 information
        // IPV6 Information
        // ///////////////////////////////////

        // Chassis Information
        ChassisInfo chassisInfo = enumSysInfoCmd.getChassisInfoEntity();
        if (null != chassisInfo) {
            cmcView.setSystemModel(chassisInfo.getSystemModel());
            cmcView.setAssetTag(chassisInfo.getSystemAssetTag());
            cmcView.setServiceTag(chassisInfo.getServiceTag());
            cmcView.setChassisName(chassisInfo.getChassisName());
            cmcView.setChassisLocation(chassisInfo.getChassisLocation());
            cmcView.setChassisMidPlaneVersion(chassisInfo.getChassisMidplaneVersion());
            cmcView.setPowerStatus(chassisInfo.getPowerStatus());
        }

        return cmcView;
    }


    private Date getDateFromString(String strDate) {
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("EEE MMM dd yyyy hh:mm");
        try {
            date = (Date) formatter.parse(strDate);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
