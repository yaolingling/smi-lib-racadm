/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.isg.smi.common.protocol.command.chassis.entity.ChassisLog;

public class EnumChassisLogsCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumChassisLogsCmd.class);
    private static String COMMAND = "getraclog -c 150";


    /**
     * @param command
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumChassisLogsCmd(String ipAddr, String userName, String passwd, boolean certCheck) {
        super(COMMAND, ipAddr, userName, passwd, certCheck);
        logger.trace("Entering constructor: EnumChassisHardwareLogsCmd" + " ipaddr=" + ipAddr + "username=" + userName);
        logger.trace("Exiting constructor: EnumChassisHardwareLogsCmd()");
    }


    /**
     * Gets all of the hardware logs for the chassis and its components
     * 
     * @return List<HardwareLogEntry>
     * @throws Exception
     */
    public List<ChassisLog> getChassisLogs() throws Exception {
        List<ChassisLog> logs = new ArrayList<ChassisLog>();
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                logs = xmlToEntity(ChassisLog.class, nodeList);
            }
        }
        return logs;
    }

}
