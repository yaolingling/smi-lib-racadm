/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dell.isg.smi.common.protocol.command.chassis.entity.ModInfo;

public class EnumModInfoCmd extends BaseEnumRacadmCmd {

    private static final Logger logger = LoggerFactory.getLogger(EnumModInfoCmd.class);

    public enum ModInfoTypeEnum {
        FAN("Fan-"), BLOWER("Blower-"), PS("PS-"), CMC("CMC-"), SWITCH("switch-"), KVM("KVM"), IO_CABLE("IO-Cable"), FPC_CABLE("FPC-Cable"), SERVER("Server-"), CHASSIS("Chassis"), SLED_STORAGE("Storage-");
        String enumValue;


        ModInfoTypeEnum(String value) {
            enumValue = value;
        }


        @Override
        public String toString() {
            return enumValue;
        }
    }


    /**
     * Constructor
     * 
     * @param ipAddr
     * @param userName
     * @param passwd
     * @param bCertCheck
     */
    public EnumModInfoCmd(String ipAddr, String userName, String passwd, boolean bCertCheck) {
        super("getmodinfo", ipAddr, userName, passwd, bCertCheck);
        if (logger.isTraceEnabled()) {
            logger.trace(String.format("Entering constructor: EnumRacadmModInfoCmd(String ipAddr - %s, String userName - %s)", ipAddr, userName));
        }
        logger.trace("Exiting constructor: EnumRacadmModInfoCmd()");
    }


    /**
     * Gets all of the entities
     * 
     * @return List of RacadModInfoEntity
     * @throws Exception
     */
    public List<ModInfo> getAllRacadmModInfo() throws Exception {
        List<ModInfo> modInfoList = null;
        if (null != this.getDocument()) {
            Node firstNode = this.getDocument().getFirstChild();
            if ((null != firstNode) && firstNode.hasChildNodes()) {
                NodeList nodeList = firstNode.getChildNodes();
                modInfoList = xmlToEntity(ModInfo.class, nodeList);
            }
        }
        return modInfoList;
    }


    /**
     * Gets the entities for a type
     * 
     * @param modInfoTypeEnum an enum of the type to return
     * @return List of RacadModInfoEntity
     * @throws Exception
     */
    public List<ModInfo> getModInfoEntitiesByType(ModInfoTypeEnum modInfoTypeEnum) throws Exception {
        List<ModInfo> modInfo = getRacadmModInfoByType(modInfoTypeEnum.enumValue);
        return modInfo;
    }


    /**
     * Gets a count of the server slots
     * 
     * @return integer count
     * @throws Exception
     */
    public int getServerSlotsCount() throws Exception {
        int slotCount = 0;
        List<ModInfo> modInfoList = null;
        String serverSlotsXpathExpressionString = "//root/entry[starts-with(module, 'Server-')]";
        modInfoList = filterUsingXpathForNodeList(ModInfo.class, serverSlotsXpathExpressionString);
        int sledOffset = calculateSledSlotOffset(modInfoList);
        if ((null != modInfoList) && (modInfoList.size() >= sledOffset)) {
            slotCount = modInfoList.size() - sledOffset;
        }
        return slotCount;
    }


    /**
     * Gets a count of the used server slots (presence field is Present or Extension)
     * 
     * @return integer count
     * @throws Exception
     */
    public int getUsedServerSlotsCount() throws Exception {
        List<ModInfo> modInfoList = null;
        String serverSlotsXpathExpressionString = "//root/entry[starts-with(module, 'Server-')]";
        modInfoList = filterUsingXpathForNodeList(ModInfo.class, serverSlotsXpathExpressionString);
        int sledOffset = calculateSledSlotOffset(modInfoList);

        String usedServerSlotsXpathExpressionString = "count(//root/entry[starts-with(module, 'Server-') and ( starts-with(presence,'Present') or starts-with(presence,'Extension'))])";
        Double usedServerSlots = filterUsingXpathForNumber(usedServerSlotsXpathExpressionString);

        // calculate used slot number
        int usedSlots = 0;
        if (null != usedServerSlots && (usedServerSlots >= sledOffset)) {
            usedSlots = usedServerSlots.intValue() - sledOffset;
        }
        return usedSlots;
    }


    private List<ModInfo> getRacadmModInfoByType(String modInfoType) throws Exception {
        List<ModInfo> modInfoList = null;
        String xpathExpressionString = String.format("//root/entry[starts-with(module,'%s')]", modInfoType);
        modInfoList = filterUsingXpathForNodeList(ModInfo.class, xpathExpressionString);
        return modInfoList;
    }


    public ModInfo getModInfoForModule(String moduleName) throws Exception {
        // List<ModInfo> modInfoList = null;
        String xpathExpressionString = String.format("//root/entry[starts-with(module,'%s')]", moduleName);
        ModInfo modInfo = this.filterUsingXpathforNode(ModInfo.class, xpathExpressionString);
        return modInfo;
        /*
         * modInfoList = filterUsingXpathForNodeList( ModInfo.class, xpathExpressionString ); if( (null != modInfoList) && (null != modInfoList.get(0)) ){ return
         * modInfoList.get(0); } return null;
         */
    }


    /**
     * When a full height sleeve is used, the extension is being reported in the ServerModInfoList. We have to calculate how many extension slots are being used by sleds, then
     * remove them from the count.
     * 
     * The problem is the extension slot is returned along with 4 quarter height slots. We normally count half heights, so we have to deduct the number of sled extension slots.
     * This method determines the number of extension slots to deduct.
     * 
     * @param serverModInfoList
     * @return sledSlotUsedOffset -- number of extensions to deduct for the sleds
     */
    private int calculateSledSlotOffset(List<ModInfo> serverModInfoList) {
        // ASSUMPTION all sleeve slots are quarter height.
        int quarterHeightSlots = 0;
        for (ModInfo modInfo : serverModInfoList) {
            if (null != modInfo) {
                String moduleName = modInfo.getModule();
                if (moduleName.length() > 1) {
                    // get the last character
                    String lastChar = moduleName.substring(moduleName.length() - 1);
                    // determine if it is not a number
                    if (!NumberUtils.isNumber(lastChar)) {
                        quarterHeightSlots++;
                    }
                }
            }
        }
        int sledSlotUsedOffset = 0;
        if (quarterHeightSlots > 1) {
            // NOTE: sledSlotUsedOffset = quarterHeightSlots / (2 half height slots make an extension)
            int halfHeightsUsed = quarterHeightSlots / 2; // int - intentionally dropping the remainder
            sledSlotUsedOffset = halfHeightsUsed / 2; // int - intentionally dropping the remainder
        }
        return sledSlotUsedOffset;
    }

}
