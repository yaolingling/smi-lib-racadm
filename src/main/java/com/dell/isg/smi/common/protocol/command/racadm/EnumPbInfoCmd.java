/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.dell.isg.smi.common.protocol.command.chassis.entity.ChassisPowerSupplyStatus;
import com.dell.isg.smi.common.protocol.command.chassis.entity.PowerBudgetStatus;
import com.dell.isg.smi.common.protocol.command.chassis.entity.ServerModulePowerAllocation;

public class EnumPbInfoCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumPbInfoCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumPbInfoCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumPbInfoCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumPbInfoCmd()");
    }


    /**
     * Gets all of the entities
     * 
     * @return List of ChassisPbInfo
     * @throws Exception
     */
    public List<ChassisPowerSupplyStatus> getChassisPowerSupplyStatus() throws Exception {
        this.setCommand("getpbinfo -metadata=excludeallbefore=Chassis_Power_Supply_Status_Table");
        String xpathExpressionString = String.format("//root/entry[@section=\"Chassis_Power_Supply_Status_Table\"]");
        List<ChassisPowerSupplyStatus> chassisPowerSupplyStatusList = filterUsingXpathForNodeList(ChassisPowerSupplyStatus.class, xpathExpressionString);
        return chassisPowerSupplyStatusList;
    }


    public List<ServerModulePowerAllocation> getServerModulePowerAllocations() throws Exception {
        this.setCommand("getpbinfo -metadata=excludeallbefore=Server_Module_Power_Allocation_Table");
        String xpathExpressionString = String.format("//root/entry[@section=\"Server_Module_Power_Allocation_Table\"]");
        List<ServerModulePowerAllocation> serverModulePowerAllocationList = filterUsingXpathForNodeList(ServerModulePowerAllocation.class, xpathExpressionString);
        return serverModulePowerAllocationList;
    }


    public PowerBudgetStatus getPowerBudgetStatus() throws Exception {
        // the Power_Budget_Status info occurs after the chassisPowerSupply table.
        this.setCommand("getpbinfo -metadata=includeallbefore=Chassis_Power_Supply_Status_Table");
        PowerBudgetStatus powerBudgetStatus = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                powerBudgetStatus = xmlToEntity(PowerBudgetStatus.class, firstNode.getFirstChild());
            }
        }
        return powerBudgetStatus;
    }
}
