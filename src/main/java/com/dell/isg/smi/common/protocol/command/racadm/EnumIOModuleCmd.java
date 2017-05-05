/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.IoInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.IoInfoStack;
import com.dell.isg.smi.common.protocol.command.chassis.entity.MacAddress;
import com.dell.isg.smi.common.protocol.command.chassis.entity.ModInfo;
import com.dell.isg.smi.common.protocol.command.chassis.entity.SwitchNicCfg;
import com.dell.isg.smi.common.protocol.command.chassis.entity.SwitchVersionSummary;
import com.dell.isg.smi.common.protocol.command.cmc.entity.IOModuleEntity;
import com.dell.isg.smi.common.protocol.command.cmc.entity.RacadmCredentials;
import com.dell.isg.smi.common.protocol.command.racadm.EnumModInfoCmd.ModInfoTypeEnum;

public class EnumIOModuleCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumChassisPowerSupplyCmd.class);

    private RacadmCredentials credentials = null;
    private String ipAddr = null;


    public EnumIOModuleCmd(String ipAddr, String userName, String passwd, boolean check) {
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


    /**
     * Control Flow: > Get Racadm getioinfo -s: for io module role, slot, number, and presence > Get Racadm getioinfo: for name, fabric, power >
     */
    public List<IOModuleEntity> execute() throws Exception {

        EnumModInfoCmd enumModInfoCmd = new EnumModInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        EnumVersionSummaryCmd enumVersionSummaryCmd = new EnumVersionSummaryCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        return execute(enumModInfoCmd, enumVersionSummaryCmd);
    }


    public List<IOModuleEntity> execute(EnumModInfoCmd enumModInfoCmd, EnumVersionSummaryCmd enumVersionSummaryCmd) throws Exception {

        // TODO: Look into having the RacadmCommandHelper open a session, and pass the session into Cmd constructors

        // Get iom info document
        // Key == IOModuleEntity.Number == RacadmIoInfoEntity.IO
        EnumIOInfoCmd enumIoInfoCmd = new EnumIOInfoCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        List<IoInfo> ioInfoList = enumIoInfoCmd.getAllRacadmIoInfo();
        List<IOModuleEntity> ioms = loadIOModuleEntityFromIOInfoList(ioInfoList);

        // Gets the Mod Info
        // This is required to the service tag.
        // Stores the Service Tag of modules keyed with module id
        // Key == Module#, Value = ServiceTag'
        // This will be merged with the resultant objects.
        List<ModInfo> modInfo = enumModInfoCmd.getModInfoEntitiesByType(ModInfoTypeEnum.SWITCH);
        HashMap<String, String> tags = getModuleServiceTags(modInfo);

        // Get the version info for IOMs
        // Document doc = this.getRacadmVersionInfoDocument();
        // Key = Module#
        // Value = IOModuleEntity with HW/FW Versions
        // This will be merged with the resultant objects.
        List<SwitchVersionSummary> switchVersionSummaryList = enumVersionSummaryCmd.getAllSwitchVersionSummarys();
        HashMap<String, IOModuleEntity> iomVersions = loadIOModuleEntityFromSwitchVersionList(switchVersionSummaryList);

        // Make a loop on the ioms array. This array was loaded with the stack command.
        // instantiate the enumNicCfgCmd used in the loop
        EnumNicCfgCmd enumNicCfgCmd = new EnumNicCfgCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
        EnumMacAddressCmd enumMacAddressCmd = new EnumMacAddressCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());

        // Get the iom slots information.
        // This command is not supported on older version of CMC.
        // Key = switch#, value = slot location
        HashMap<String, String> iomSlots = null;
        try {
            EnumIOInfoStackCmd enumIoInfoStackCmd = new EnumIOInfoStackCmd(this.ipAddr, this.credentials.getUserName(), this.credentials.getPassword(), this.credentials.isCertificateCheck());
            List<IoInfoStack> ioInfoStackList = enumIoInfoStackCmd.getAllIoInfoStack();
            iomSlots = this.getIOMSlotsFromIOInfoStackList(ioInfoStackList);
        } catch (Exception e) {
            logger.debug("Stack command not available");
        }

        // If the command fails due to an older firmware version, then assume the slot locations.
        if (iomSlots == null) {
            iomSlots = new HashMap<String, String>();
            // XRTX got 1 switch attached to slot A
            if (ioms != null && ioms.size() == 1) {
                iomSlots.put("switch-1", "A");
            }
            // Assume the slots for the max 6 switches.
            else {
                iomSlots.put("switch-1", "A1");
                iomSlots.put("switch-2", "A2");
                iomSlots.put("switch-3", "B1");
                iomSlots.put("switch-4", "B2");
                iomSlots.put("switch-5", "C1");
                iomSlots.put("switch-6", "C2");
            }
        }

        // Now merge the ioms array with the output of other commands.
        for (IOModuleEntity entity : ioms) {

            // Gets the service tag
            if (tags != null && tags.containsKey(entity.getNumber().toLowerCase())) {
                entity.setServiceTag(tags.get(entity.getNumber().toLowerCase()));
            }

            // Gets the nic config
            SwitchNicCfg switchNicCfg = enumNicCfgCmd.getNicCfgForSwitch(entity.getNumber());
            if (null != switchNicCfg) {
                if (null != switchNicCfg.getDHCPEnabled() && switchNicCfg.getDHCPEnabled().equals("1")) {
                    entity.setDhcpEnbaled(true);
                }
                entity.setGateway(switchNicCfg.getGateway());
                entity.setIpAddress(switchNicCfg.getIPAddress());
                entity.setSubnetMask(switchNicCfg.getSubnetMask());
            }

            // Gets the version info
            IOModuleEntity iomVersion = iomVersions.get(entity.getNumber().toLowerCase());
            if (iomVersion != null) {
                entity.setHardwareVersion(iomVersion.getHardwareVersion());
                entity.setFirmwareVersion(iomVersion.getFirmwareVersion());

            }

            // Gets the Mac Address
            MacAddress address = enumMacAddressCmd.getMacAddressByModule(entity.getNumber());
            if (address != null) {
                entity.setMacAddress(address.getNIC1MACAddress());
            }

            entity.setSlot(iomSlots.get(entity.getNumber().toLowerCase()));
        }

        return ioms;

    }


    ////////////////////////////// Get and Parse IOM Stack Information////////////////////////////////////////////
    private HashMap<String, String> getIOMSlotsFromIOInfoStackList(List<IoInfoStack> ioInfoStackList) {
        HashMap<String, String> iomSlots = null;
        if ((null != ioInfoStackList) && !ioInfoStackList.isEmpty()) {
            iomSlots = new HashMap<String, String>();
            for (IoInfoStack ioInfoStack : ioInfoStackList) {
                if (null != ioInfoStack) {
                    if (ioInfoStack.getIO() != null && !iomSlots.containsKey(ioInfoStack.getIO().toLowerCase()))
                        iomSlots.put(ioInfoStack.getIO().toLowerCase(), ioInfoStack.getSlot());
                }
            }
        }
        return iomSlots;
    }
    ///////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////// Get and Parse IOM Information///////////////////////////////////
    private List<IOModuleEntity> loadIOModuleEntityFromIOInfoList(List<IoInfo> ioInfoList) {
        List<IOModuleEntity> iomList = null;
        if ((null != ioInfoList) && !ioInfoList.isEmpty()) {
            iomList = new LinkedList<IOModuleEntity>();
            for (IoInfo ioInfo : ioInfoList) {
                if (null != ioInfo) {
                    IOModuleEntity entity = new IOModuleEntity();
                    // name
                    if (null != ioInfo.getName()) {
                        entity.setName(ioInfo.getName());
                    }
                    // fabric == type
                    if (null != ioInfo.getType()) {
                        entity.setFabric(ioInfo.getType());
                    }
                    // number == io
                    if (null != ioInfo.getIO()) {
                        entity.setNumber(ioInfo.getIO());
                    }
                    // power status
                    if (null != ioInfo.getPower()) {
                        entity.setPowerStatus(ioInfo.getPower());
                    }

                    // presence
                    if (null != ioInfo.getPresence()) {
                        if (ioInfo.getPresence().trim().equalsIgnoreCase("present")) {
                            entity.setPresent(true);
                        }
                    }

                    // role
                    if (null != ioInfo.getRole()) {
                        entity.setRole(ioInfo.getRole());
                    }

                    entity.setSlot("NA");

                    iomList.add(entity);
                }
            }
        }
        return iomList;
    }

    ///////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////// Get and Parse IOM Version Information/////////////////////////////////////
    private HashMap<String, IOModuleEntity> loadIOModuleEntityFromSwitchVersionList(List<SwitchVersionSummary> switchVersionSummaryList) {
        HashMap<String, IOModuleEntity> ioModuleEntityHashMap = new HashMap<String, IOModuleEntity>();
        if ((null != switchVersionSummaryList) && !switchVersionSummaryList.isEmpty()) {
            ioModuleEntityHashMap = new HashMap<String, IOModuleEntity>();
            for (SwitchVersionSummary switchVersionSummary : switchVersionSummaryList) {
                if (null != switchVersionSummary) {
                    IOModuleEntity entity = new IOModuleEntity();
                    // set number from switch
                    if (null != switchVersionSummary.getSwitch()) {
                        entity.setNumber(switchVersionSummary.getSwitch());
                    }
                    // hardware version
                    if (null != switchVersionSummary.getHWVersion()) {
                        entity.setHardwareVersion(switchVersionSummary.getHWVersion());
                    }
                    // firmware version
                    if (null != switchVersionSummary.getFWVersion()) {
                        entity.setFirmwareVersion(switchVersionSummary.getFWVersion());
                    }

                    // set the entity into the hashmap
                    if (entity.getNumber() != null && !ioModuleEntityHashMap.containsKey(entity.getNumber().toLowerCase())) {
                        ioModuleEntityHashMap.put(entity.getNumber().toLowerCase(), entity);
                    }

                }
            }
        }
        return ioModuleEntityHashMap;
    }


    /**
     * Returns the service tags for ioms
     * 
     * @param doc
     * @return
     */
    private HashMap<String, String> getModuleServiceTags(List<ModInfo> modInfoList) {
        HashMap<String, String> serviceTagHashMap = null;
        if ((null != modInfoList) && !modInfoList.isEmpty()) {
            serviceTagHashMap = new HashMap<String, String>();
            for (ModInfo modInfo : modInfoList) {

                // if the entity or service tag is null, or the module is blank (includes null check), continue to the next record
                if ((null == modInfo) || (null == modInfo.getSvcTag()) || StringUtils.isBlank(modInfo.getModule())) {
                    continue; // to the next record
                }

                // otherwise, if the module isn't blank and doesn't exist already in the map, add it and the service tag to the map.
                String moduleName = modInfo.getModule();
                if (!serviceTagHashMap.containsKey(moduleName.toLowerCase())) {
                    serviceTagHashMap.put(moduleName.toLowerCase(), modInfo.getSvcTag());
                }
            }
        }
        return serviceTagHashMap;
    }
}
