package com.kk.arch.commom.util;

import com.kk.arch.common.util.Md5Utils;

public class Md5UtilsTest {

    public static void main(String[] args) {
        String plainText = "123456";//明文
        String saltValue = "8";//盐值

        System.out.println(Md5Utils.md5(plainText));
        System.out.println(Md5Utils.md5Upper(plainText));
        System.out.println(Md5Utils.md5Lower16Digit(plainText));
        System.out.println(Md5Utils.md5Upper16Digit(plainText));

        System.out.println("====================");

        System.out.println(Md5Utils.md5(plainText, saltValue));
        System.out.println(Md5Utils.md5Upper(plainText, saltValue));
        System.out.println(Md5Utils.md5Lower16Digit(plainText, saltValue));
        System.out.println(Md5Utils.md5Upper16Digit(plainText, saltValue));


        plainText = "我的朋友";//明文
        saltValue = "aaabbb";//盐值

        System.out.println(Md5Utils.md5(plainText));
        System.out.println(Md5Utils.md5Upper(plainText));
        System.out.println(Md5Utils.md5Lower16Digit(plainText));
        System.out.println(Md5Utils.md5Upper16Digit(plainText));

        System.out.println("====================");

        System.out.println(Md5Utils.md5(plainText, saltValue));
        System.out.println(Md5Utils.md5Upper(plainText, saltValue));
        System.out.println(Md5Utils.md5Lower16Digit(plainText, saltValue));
        System.out.println(Md5Utils.md5Upper16Digit(plainText, saltValue));

    }

}
