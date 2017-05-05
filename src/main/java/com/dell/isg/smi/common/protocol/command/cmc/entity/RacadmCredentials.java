/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.command.cmc.entity;

/**
 * @author rahman.muhammad
 *
 */
public class RacadmCredentials {
    private String address;
    private String userName;
    private String password;
    boolean certificateCheck;


    public RacadmCredentials() {

    }


    public RacadmCredentials(String address, String userName, String password, boolean certificateCheck) {
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.certificateCheck = certificateCheck;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isCertificateCheck() {
        return certificateCheck;
    }


    public void setCertificateCheck(boolean certificateCheck) {
        this.certificateCheck = certificateCheck;
    }

}
