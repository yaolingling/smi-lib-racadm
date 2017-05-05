/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumLicenseViewCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumLicenseViewCmd.class);


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumLicenseViewCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("license view", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumFanOffsetCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumFanOffsetCmd()");
    }

    // public List<PhysicalDisk> getAllPhysicalDisks() throws Exception{
    // this.setCommand("raid get pdisks -o");
    // List<PhysicalDisk> physicalDiskList = null;
    // if(null != this.getDocument()){
    // Node firstNode = this.getDocument().getFirstChild();
    // if( (null != firstNode) && firstNode.hasChildNodes() ){
    // NodeList nodeList = firstNode.getChildNodes();
    // physicalDiskList = xmlToEntity(PhysicalDisk.class, nodeList);
    // }
    // }
    // return physicalDiskList;
    // }

    /**
     * Gets all of the entities
     * 
     * @return LicenseViewInfo
     * @throws Exception
     */
    /*
     * public List<LicenseViewInfo> getLicenseView() throws Exception{ List<LicenseViewInfo> licenseViewInfo = null; if(null != this.getDocument()){ Node firstNode =
     * this.getDocument().getFirstChild(); if( (null != firstNode) && firstNode.hasChildNodes() ){ NodeList nodeList = firstNode.getChildNodes(); licenseViewInfo =
     * xmlToEntity(LicenseViewInfo.class, nodeList); } } return licenseViewInfo; }
     */
}
