/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dell.isg.smi.common.protocol.command.chassis.entity.PcieSlot;
import com.dell.isg.smi.common.protocol.command.chassis.entity.PcieSlotFQDD;
import com.dell.isg.smi.commons.elm.exception.RuntimeCoreException;
import com.dell.isg.smi.common.protocol.command.cmc.entity.ChassisPCIeEntity;

public class EnumChassisPCIeCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumChassisPCIeCmd.class);
    EnumPcieCfgCmd enumPcieCfgCmd = null;


    public EnumChassisPCIeCmd(String ipAddr, String userName, String passwd, boolean check) {
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumChassisPCIeCmd(String ipAddr - %s, String userName - %s, String passwd - %s)", ipAddr, userName, "####"));
        }
        enumPcieCfgCmd = new EnumPcieCfgCmd(ipAddr, userName, passwd, check);
        logger.trace("Exiting constructor: EnumChassisPCIeCmd()");
    }


    public List<ChassisPCIeEntity> execute() throws Exception {
        List<ChassisPCIeEntity> pcieSlotEntities = new ArrayList<ChassisPCIeEntity>();
        try {
            HashSet<String> pcieSlots = this.getFqddHashSet();

            if (pcieSlots != null) {
                Iterator<String> keys = pcieSlots.iterator();
                while (keys.hasNext()) {
                    String key = keys.next();
                    try {
                        PcieSlot pcieSlot = enumPcieCfgCmd.getPcieSlot(key);
                        if (null != pcieSlot) {
                            ChassisPCIeEntity entity = new ChassisPCIeEntity();
                            if (pcieSlot.getAdapterPresenceStatus() != null) {
                                entity.setAdapterPresent(pcieSlot.getAdapterPresenceStatus());
                            } else {
                                entity.setAdapterPresent(pcieSlot.getAdapterPresence());
                            }
                            entity.setAssignmentStatus(pcieSlot.getAssignmentStatus());
                            entity.setSlotNumber(pcieSlot.getPCIeSlot());
                            entity.setSlotName(pcieSlot.getName());
                            if (entity.getSlotName() != null && entity.getSlotName().equalsIgnoreCase("n/a")) {
                                entity.setSlotName("Empty");
                            }
                            entity.setPowerState(pcieSlot.getPowerStatus());
                            if (pcieSlot.getAllocatedSlotPower() != null) {
                                entity.setAllocatedSlotPower(pcieSlot.getAllocatedSlotPower());
                            } else {
                                entity.setAllocatedSlotPower(pcieSlot.getRequiredAdapterPower());
                            }
                            entity.setServerMapping(pcieSlot.getServerMapping());
                            entity.setServerSlot(pcieSlot.getServerSlot());
                            if (entity.getServerSlot() == null || entity.getServerSlot().equals("0")) {
                                entity.setServerSlot("N/A");
                            }
                            entity.setSlotType(pcieSlot.getSlotType());
                            entity.setPciDeviceId(pcieSlot.getPCIDeviceID());
                            entity.setPciVendorId(pcieSlot.getPCIVendorID());
                            entity.setFabric(pcieSlot.getFabric());
                            pcieSlotEntities.add(entity);
                        }
                    } catch (Exception e) {
                        logger.warn("Failed to get the PCIe card info");
                        logger.warn(e.getMessage());
                    }
                }
            }
        } catch (RuntimeCoreException e) {
            // This can happen in case of M10003 because PCIe slots are not applicable to that chassis.
            logger.warn(e.getMessage());
        }
        return pcieSlotEntities;
    }


    private HashSet<String> getFqddHashSet() throws Exception {
        List<PcieSlotFQDD> pcieSlotFQDDList = enumPcieCfgCmd.getAllPcieSlotFQDD();
        HashSet<String> pcieSlots = new HashSet<String>();
        if ((null != pcieSlotFQDDList) && !pcieSlotFQDDList.isEmpty()) {
            for (PcieSlotFQDD pcieSlotFQDD : pcieSlotFQDDList) {
                if (null != pcieSlotFQDD) {
                    String fqdd = pcieSlotFQDD.getFQDD();
                    if (fqdd.toLowerCase().startsWith("pcie") && !pcieSlots.contains(fqdd)) {
                        pcieSlots.add(fqdd);
                    }
                }
            }
        }
        return pcieSlots;
    }

}
