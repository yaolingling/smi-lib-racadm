/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.isg.smi.common.protocol.command.chassis.entity.PcieSlot;
import com.dell.isg.smi.common.protocol.command.chassis.entity.PcieSlotAssignment;
import com.dell.isg.smi.common.protocol.command.chassis.entity.PcieSlotFQDD;

public class EnumPcieCfgCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumPcieCfgCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumPcieCfgCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumPcieCfgCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumPcieCfgCmd()");
    }


    public List<PcieSlotFQDD> getAllPcieSlotFQDD() throws Exception {
        this.setCommand("getpciecfg");
        List<PcieSlotFQDD> pcieSlotFQDD = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                pcieSlotFQDD = xmlToEntity(PcieSlotFQDD.class, nodeList);
            }
        }
        return pcieSlotFQDD;
    }


    public List<PcieSlotAssignment> getAllPcieSlotAssignments() throws Exception {
        this.setCommand("getpciecfg -a");
        List<PcieSlotAssignment> pcieSlotAssignmentList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                pcieSlotAssignmentList = xmlToEntity(PcieSlotAssignment.class, nodeList);
            }
        }
        return pcieSlotAssignmentList;
    }


    public PcieSlot getPcieSlot(String fqdd) throws Exception {
        this.setCommand(String.format("getpciecfg -c %s", fqdd));
        PcieSlot pcieSlot = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                pcieSlot = xmlToEntity(PcieSlot.class, firstNode.getFirstChild());
            }
        }
        return pcieSlot;
    }
}
