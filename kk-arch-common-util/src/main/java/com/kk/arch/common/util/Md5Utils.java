package com.kk.arch.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.Character.MAX_RADIX;

/**
 * MD5加密/验证工具类
 * @author Zal
 */
public class Md5Utils {

    private static final int RADIX = MAX_RADIX;

    private static MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 不加盐值32位小写
     */
    public static String md5(String plainText) {
        String md5 = null;
        if (null != plainText && !plainText.isEmpty()) {
            MessageDigest md = getMessageDigest();
            md.update(plainText.getBytes(StandardCharsets.UTF_8));
            md5 = new BigInteger(1, md.digest()).toString(RADIX);
        }
        return md5;
    }

    /**
     * 加盐值32位小写
     */
    public static String md5(String plainText, String saltValue) {
        String md5 = null;
        if (null != plainText && !plainText.isEmpty() && null != saltValue && !saltValue.isEmpty()) {
            MessageDigest md = getMessageDigest();
            md.update(plainText.getBytes(StandardCharsets.UTF_8));
            md.update(saltValue.getBytes(StandardCharsets.UTF_8));
            md5 = new BigInteger(1, md.digest()).toString(RADIX);
        }
        return md5;
    }

    /**
     * 不加盐值16位小写
     */
    public static String md5Lower16Digit(String plainText) {
        String md5 = md5(plainText);
        return null == md5 ? null : md5.substring(8, 24);
    }

    /**
     * 加盐值16位小写
     */
    public static String md5Lower16Digit(String plainText, String saltValue) {
        String md5 = md5(plainText, saltValue);
        return null == md5 ? null : md5.substring(8, 24);
    }

    /**
     * 不加盐值16位大写
     */
    public static String md5Upper16Digit(String plainText) {
        String md5 = md5Lower16Digit(plainText);
        return null == md5 ? null : md5.toUpperCase();
    }

    /**
     * 加盐值16位大写
     */
    public static String md5Upper16Digit(String plainText, String saltValue) {
        String md5 = md5Lower16Digit(plainText, saltValue);
        return null == md5 ? null : md5.toUpperCase();
    }

    /**
     * 不加盐值32位大写
     */
    public static String md5Upper(String plainText) {
        String md5 = md5(plainText);
        return null == md5 ? null : md5.toUpperCase();
    }

    /**
     * 加盐值32位大写
     */
    public static String md5Upper(String plainText, String saltValue) {
        String md5 = md5(plainText, saltValue);
        return null == md5 ? null : md5.toUpperCase();
    }

}