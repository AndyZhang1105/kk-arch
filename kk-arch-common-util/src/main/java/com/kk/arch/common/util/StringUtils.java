package com.kk.arch.common.util;

/**
 * @author Zal
 */
public class StringUtils {

    public static boolean isBlank(String args) {
        return args == null || args.isBlank();
    }

    public static boolean isNotBlank(String args) {
        return args != null && !args.isBlank();
    }

}
