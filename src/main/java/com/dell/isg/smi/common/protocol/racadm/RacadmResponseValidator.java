/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.smi.common.protocol.racadm;

import java.util.regex.Pattern;

/**
 *
 */
public class RacadmResponseValidator {
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{4,32}$";
    // private static final String PASSWORD_PATTERN = "^[a-z0-9_-!@#$%^&*()+=]{6,20}$";
    // Must escape special characters and put - at end to avoid parse exception
    private static final String PASSWORD_PATTERN = "^[-a-z0-9_!@#$%\\^&*\\(\\)+=]{6,20}$";


    public final static boolean isIPAddressValid(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        if (parts.length != 4)
            return false;
        for (String s : parts) {
            int i = Integer.parseInt(s);
            if ((i < 0) || (i > 255))
                return false;
        }
        return true;
    }


    public static boolean isStringEmpty(String s) {
        return (s == null || s.trim().length() == 0);
    }


    public static final boolean isUserNameValid(String str) {
        Pattern userNamePattern = Pattern.compile(USERNAME_PATTERN);
        return userNamePattern.matcher(str).matches();
    }


    public static final boolean isPasswordValid(String str) {
        Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        return passwordPattern.matcher(str).matches();
    }
}
