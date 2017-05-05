/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.isg.smi.common.protocol.command.chassis.entity.ChassisInfrastructureVersion;
import com.dell.isg.smi.common.protocol.command.chassis.entity.CmcVersion;
import com.dell.isg.smi.common.protocol.command.chassis.entity.PhysicalDiskVersion;
import com.dell.isg.smi.common.protocol.command.chassis.entity.StorageControllerVersion;
import com.dell.isg.smi.common.protocol.command.chassis.entity.StorageEnclosureVersion;
import com.dell.isg.smi.common.protocol.command.chassis.entity.SwitchVersion;

public class EnumVersionCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumVersionCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumVersionCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumVersionCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumVersionCmd()");
    }


    /**
     * Gets all physical disks
     * 
     * @return List<PhysicalDiskVersion>
     * @throws Exception
     */
    public List<PhysicalDiskVersion> getAllPhysicalDiskVersions() throws Exception {
        this.setCommand("getversion -m hdd");
        List<PhysicalDiskVersion> physicalDiskVersionList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                physicalDiskVersionList = xmlToEntity(PhysicalDiskVersion.class, nodeList);
            }
        }
        return physicalDiskVersionList;
    }


    /**
     * Gets all storage enclosures
     * 
     * @return List<StorageEnclosureVersion>
     * @throws Exception
     */
    public List<StorageEnclosureVersion> getAllStorageEnclosures() throws Exception {
        this.setCommand("getversion -m expander");
        List<StorageEnclosureVersion> storageEnclosureVersionList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                storageEnclosureVersionList = xmlToEntity(StorageEnclosureVersion.class, nodeList);
            }
        }
        return storageEnclosureVersionList;
    }


    /**
     * Gets all storage controllers
     * 
     * @return List<StorageControllerVersion>
     * @throws Exception
     */
    public List<StorageControllerVersion> getAllStorageControllers() throws Exception {
        this.setCommand("getversion -m perc");
        List<StorageControllerVersion> storageControllerVersionList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                storageControllerVersionList = xmlToEntity(StorageControllerVersion.class, nodeList);
            }
        }
        return storageControllerVersionList;
    }


    /**
     * Gets the version object for the switch
     * 
     * @param switchName name of the switch
     * @return SwitchVersion
     * @throws Exception
     */
    public SwitchVersion getSwitchVersion(String switchName) throws Exception {
        this.setCommand(String.format("getversion -m %s", switchName));
        SwitchVersion switchVersion = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                switchVersion = xmlToEntity(SwitchVersion.class, firstNode.getFirstChild());
            }
        }
        return switchVersion;
    }


    /**
     * Gets the version object for the CMC
     * 
     * @param cmcName name of the cmc
     * @return CmcVersion
     * @throws Exception
     */
    public CmcVersion getCmcVersion(String cmcName) throws Exception {
        this.setCommand(String.format("getversion -m %s", cmcName));
        CmcVersion cmcVersion = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                cmcVersion = xmlToEntity(CmcVersion.class, firstNode.getFirstChild());
            }
        }
        return cmcVersion;
    }


    /**
     * Gets the version object for a chassis infrastructure device
     * 
     * @param ChassisInfrastructureName -- name of a chassis infrastructre device known names: main-board
     * @return ChassisInfrastructureVersion
     * @throws Exception
     */
    public ChassisInfrastructureVersion getChassisInfrastructureVersion(String ChassisInfrastructureName) throws Exception {
        this.setCommand(String.format("getversion -m %s", ChassisInfrastructureName));
        ChassisInfrastructureVersion chassisInfrastructureVersion = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                chassisInfrastructureVersion = xmlToEntity(ChassisInfrastructureVersion.class, firstNode.getFirstChild());
            }
        }
        return chassisInfrastructureVersion;
    }


    /**
     * Gets the version object for a storage enclosure
     * 
     * @param storageEnclosureFQDD -- fqdd for the storage enclosure
     * @return StorageEnclosureVersion
     * @throws Exception
     */
    public StorageEnclosureVersion getStorageEnclosureVersion(String storageEnclosureFQDD) throws Exception {
        this.setCommand(String.format("getversion -m %s", storageEnclosureFQDD));
        StorageEnclosureVersion storageEnclosureVersion = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                storageEnclosureVersion = xmlToEntity(StorageEnclosureVersion.class, firstNode.getFirstChild());
            }
        }
        return storageEnclosureVersion;
    }


    /**
     * Gets the version object for a storage controller
     * 
     * @param storageControllerFQDD -- fqdd for the storage controller
     * @return
     * @throws Exception
     */
    public StorageControllerVersion getStorageControllerVersion(String storageControllerFQDD) throws Exception {
        this.setCommand(String.format("getversion -m %s", storageControllerFQDD));
        StorageControllerVersion storageControllerVersion = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                storageControllerVersion = xmlToEntity(StorageControllerVersion.class, firstNode.getFirstChild());
            }
        }
        return storageControllerVersion;
    }


    /**
     * Gets the version object for a physical disk
     * 
     * @param physicalDiskFQDD -- FQDD for the physical disk
     * @return PhysicalDiskVersion
     * @throws Exception
     */
    public PhysicalDiskVersion getPhysicalDiskVersion(String physicalDiskFQDD) throws Exception {
        this.setCommand(String.format("getversion -m %s", physicalDiskFQDD));
        PhysicalDiskVersion physicalDiskVersion = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                physicalDiskVersion = xmlToEntity(PhysicalDiskVersion.class, firstNode.getFirstChild());
            }
        }
        return physicalDiskVersion;
    }

}
