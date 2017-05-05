/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.racadm;

public class EnumModularChassisViewCmd {

}
/*
 * FIX ME FOR ICEE
 * 
 * import org.apache.logging.log4j.Logger; import com.dell.esg.icee.common.logging.CoreLogManager; import org.w3c.dom.Element; import org.w3c.dom.Node; import org.w3c.dom.NodeList;
 * 
 * import com.dell.esg.icee.common.protocol.command.cmc.entity.ModularChassisViewEntity; import com.dell.esg.icee.common.protocol.wsman.WSCommandRNDConstant; import
 * com.dell.esg.icee.common.protocol.wsman.WSManBaseCommand; import com.dell.esg.icee.common.protocol.wsman.WSManageSession;
 * 
 * 
 * 
 * 
 * public class EnumModularChassisViewCmd extends WSManBaseCommand {
 * 
 * private WSManageSession session = null; private static final Logger logger = LoggerFactory.getLogger(EnumModularChassisViewCmd.class);
 * 
 * public EnumModularChassisViewCmd(String ipAddr, String userName,String passwd, boolean check) {
 * 
 * // set the WSMan Session super(ipAddr, userName, passwd, check); if(logger.isTraceEnabled()){
 * logger.trace(String.format("Entering constructor: EnumModularChassisViewCmd(String ipAddr - %s, String userName - %s, String passwd - %s)", ipAddr, userName, "####")); } session
 * = this.getSession(); session.setResourceUri(getResourceURI());
 * 
 * logger.trace("Exiting constructor: EnumModularChassisViewCmd()");
 * 
 * }
 * 
 * 
 * @Override public ModularChassisViewEntity execute() throws Exception { ModularChassisViewEntity chassisView = new ModularChassisViewEntity();
 * 
 * NodeList response = this.sendRequestEnumerationReturnNodeList(); if( null != response){ response=response.item(0).getChildNodes(); for(int i = 0 ; i < response.getLength(); i++)
 * {
 * 
 * Node itemNode = response.item(i); chassisView.setAssetTag(getKeyValue(itemNode,"AssetTag")); // m1000e, plasma chassisView.setCmcModel(getKeyValue(itemNode,"CMCModel")); //
 * plasma chassisView.setCaption(getKeyValue(itemNode,"Caption")); // m1000e, plasma
 * chassisView.setChassisDefaultLowerPowerCap(getKeyValue(itemNode,"ChassisDefaultLowerPowerCap")); // m1000e
 * chassisView.setChassisDefaultUpperPowerCap(getKeyValue(itemNode,"ChassisDefaultUpperPowerCap")); // m1000e
 * chassisView.setChassisExternalPowerCap(getKeyValue(itemNode,"ChassisExternalPowerCap")); // m1000e chassisView.setDescription(getKeyValue(itemNode,"Description")); // m1000e,
 * plasma chassisView.setDnsDomainName(getKeyValue(itemNode,"DNSDomainName")); // plasma chassisView.setElementName(getKeyValue(itemNode,"ElementName")); // m1000e, plasma
 * chassisView.setEnhancedCoolingMode(getKeyValue(itemNode,"EnhancedCoolingMode")); // plasma chassisView.setExpressServiceCode(getKeyValue(itemNode,"ExpressServiceCode")); //
 * plasma chassisView.setFQDD(getKeyValue(itemNode,"FQDD")); // m1000e, plasma chassisView.setFlexFabricStateDescription(getKeyValue(itemNode,"FlexFabricState")); // m1000e, plasma
 * chassisView.setFlexFabricStateDescription(getKeyValue(itemNode,"FlexFabricStateDescription")); // m1000e, plasma chassisView.setGeneration(getKeyValue(itemNode,"Generation"));
 * // m1000e, plasma chassisView.setHostName(getKeyValue(itemNode,"HostName")); // m1000e, plasma chassisView.setIpv4Address(getKeyValue(itemNode,"IPv4Address")); // m1000e, plasma
 * chassisView.setInstanceID(getKeyValue(itemNode,"InstanceID")); // m1000e, plasma chassisView.setLocation(getKeyValue(itemNode,"Location")); // m1000e, plasma
 * chassisView.setMgmtContollerFirmwareVersion(getKeyValue(itemNode,"MgmtControllerFirmwareVersion")); // m1000e, plasma chassisView.setModel( getKeyValue(itemNode,"Model") );
 * chassisView.setPhysicalLocationAisle(getKeyValue(itemNode,"PhysicalLocationAisle")); // m1000e, plasma
 * chassisView.setPhysicalLocationChassisName(getKeyValue(itemNode,"PhysicalLocationChassisName")); // m1000e, plasma
 * chassisView.setPhysicalLocationDataCenter(getKeyValue(itemNode,"PhysicalLocationDataCenter")); // m1000e, plasma
 * chassisView.setPhysicalLocationRack(getKeyValue(itemNode,"PhysicalLocationRack")); // m1000e, plasma
 * chassisView.setPhysicalLocationRackSlot(getKeyValue(itemNode,"PhysicalLocationRackSlot")); // m1000e, plasma chassisView.setPowerState(getKeyValue(itemNode,"PowerState")); //
 * m1000e, plasma chassisView.setPrimaryStatus(getKeyValue(itemNode,"PrimaryStatus")); // m1000e, plasma
 * chassisView.setPwrInputInfrastructureAllocation(getKeyValue(itemNode,"PwrInputInfrastructureAllocation")); // m1000e, plasma
 * chassisView.setPwrInputSystemConsumption(getKeyValue(itemNode,"PwrInputSystemConsumption")); // m1000e, plasma
 * chassisView.setSNMPCommunityBladeIRAlert(getKeyValue(itemNode,"SNMPCommunityBladeIRAlert")); // m1000e, plasma
 * chassisView.setSNMPDestinationBladeIRAlert(getKeyValue(itemNode,"SNMPDestinationBladeIRAlert")); // m1000e, plasma
 * chassisView.setServerBasedPowerMgmtEnableTime(getKeyValue(itemNode,"ServerBasedPowerMgmtEnableTime")); // m1000e, plasma
 * chassisView.setServerBasedPowerMgmtEnabled(getKeyValue(itemNode,"ServerBasedPowerMgmtEnabled")); // m1000e, plasma chassisView.setServiceTag(getKeyValue(itemNode,"ServiceTag"));
 * // m1000e, plasma chassisView.setSystemID(getKeyValue(itemNode,"SystemID")); // plasma chassisView.setSystemPSUInputPower(getKeyValue(itemNode,"SystemPSUInputPower")); // m1000e
 * chassisView.setSystemPSUOutputPower(getKeyValue(itemNode,"SystemPSUOutputPower")); // m1000e chassisView.setUrlString(getKeyValue(itemNode,"URLString")); // plasma
 * chassisView.setUseHostNameForSlotName(getKeyValue(itemNode,"UseHostNameForSlotName ")); // m1000e, plasma
 * 
 * } } return chassisView;
 * 
 * }
 * 
 * 
 * private String getResourceURI() { StringBuilder sb = new StringBuilder(WSCommandRNDConstant.WSMAN_BASE_URI); sb.append(WSCommandRNDConstant.WS_OS_SVC_NAMESPACE);
 * sb.append(WSManClassEnum.DCIM_ModularChassisView); return sb.toString(); }
 * 
 * 
 * 
 * private String getKeyValue(Node node, String key) { String value = ""; try { NodeList childList = node.getChildNodes(); for(int i=0; i<childList.getLength(); i++) { Node
 * childNode = childList.item(i); if(childNode.getLocalName().equals(key)) { if(childNode.hasChildNodes()){ NodeList nodeList1 = childNode.getChildNodes(); for(int k=0;
 * k<nodeList1.getLength(); k++){ Node finalNode = nodeList1.item(k); if(Element.TEXT_NODE == finalNode.getNodeType()) { value = finalNode.getNodeValue(); break; } }
 * 
 * if(!value.isEmpty()) { break; } } } } }catch(Exception exp) { logger.error(exp); }
 * 
 * return value; } }
 */