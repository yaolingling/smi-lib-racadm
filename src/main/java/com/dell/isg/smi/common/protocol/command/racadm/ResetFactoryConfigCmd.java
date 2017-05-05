/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author prashanth.gowda
 *
 */
public class ResetFactoryConfigCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(ResetFactoryConfigCmd.class);
    private static String COMMAND = "racresetcfg";
    private String module;


    /**
     * @return the module
     */
    public String getModule() {
        return module;
    }


    /**
     * @param module the module to set
     */
    public void setModule(String module) {
        this.module = module;
    }


    public ResetFactoryConfigCmd(String address, String username, String password, boolean certificateCheck) {
        super(COMMAND, address, username, password, certificateCheck);
        logger.trace("Exiting constructor: ResetFactoryConfigCmd(address{}, username {})", address, username);
    }


    public void execute() throws Exception {
        logger.trace("Entering execute()");
        setCommand(COMMAND + getModuleParameter());
        getResult();
        logger.trace("exiting execute()");
    }


    private String getModuleParameter() {
        String moduleParameter = "";
        if (!StringUtils.isEmpty(getModule())) {
            moduleParameter = " -m " + getModule();
        }
        return moduleParameter;
    }
}