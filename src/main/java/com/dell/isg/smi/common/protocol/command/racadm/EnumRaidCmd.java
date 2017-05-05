/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.isg.smi.common.protocol.command.chassis.entity.DiskEnclosure;
import com.dell.isg.smi.common.protocol.command.chassis.entity.PhysicalDisk;
import com.dell.isg.smi.common.protocol.command.chassis.entity.RaidController;
import com.dell.isg.smi.common.protocol.command.chassis.entity.VirtualDisk;

public class EnumRaidCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumRaidCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumRaidCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumRaidCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumRaidCmd()");
    }


    public List<RaidController> getAllRaidControllers() throws Exception {
        this.setCommand("raid get controllers -o");
        List<RaidController> raidControllerList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                raidControllerList = xmlToEntity(RaidController.class, nodeList);
            }
        }
        return raidControllerList;
    }


    public List<DiskEnclosure> getAllDiskEnclosures() throws Exception {
        this.setCommand("raid get enclosures -o");
        List<DiskEnclosure> diskEnclosureList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                diskEnclosureList = xmlToEntity(DiskEnclosure.class, nodeList);
            }
        }
        return diskEnclosureList;
    }


    public List<PhysicalDisk> getAllPhysicalDisks() throws Exception {
        this.setCommand("raid get pdisks -o");
        List<PhysicalDisk> physicalDiskList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                physicalDiskList = xmlToEntity(PhysicalDisk.class, nodeList);
            }
        }
        return physicalDiskList;
    }


    public List<VirtualDisk> getAllVirtualDisks() throws Exception {
        this.setCommand("raid get vdisks -o");
        List<VirtualDisk> virtualDiskList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                virtualDiskList = xmlToEntity(VirtualDisk.class, nodeList);
            }
        }
        return virtualDiskList;
    }

}
