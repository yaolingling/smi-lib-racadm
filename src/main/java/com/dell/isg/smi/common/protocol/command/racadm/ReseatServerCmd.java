/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rajesh.varada
 *
 */
public class ReseatServerCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(ReseatServerCmd.class);
    private static String COMMAND = "serveraction -f -m server-%s reseat";
    private long slotNumber;


    public ReseatServerCmd(String address, String username, String password, boolean certificateCheck, long slotNumber) {
        super(COMMAND, address, username, password, certificateCheck);
        this.slotNumber = slotNumber;
        logger.trace("Exiting constructor: ReseatServerCmd (address {}, username {})", address, username);
    }


    public String execute() throws Exception {
        logger.trace("Entered execute()");
        setCommand(String.format(COMMAND, getSlotNumber()));
        return getResult();
    }


    /**
     * @return the slotNumber
     */
    public long getSlotNumber() {
        return slotNumber;
    }

}