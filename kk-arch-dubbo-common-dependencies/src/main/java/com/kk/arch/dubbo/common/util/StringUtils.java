package com.kk.arch.dubbo.common.util;

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

    /**
     * 将下划线分隔的字符串转换为驼峰命名法。
     * 例如：user_name -> userName
     * @param snakeCase 下划线分隔的字符串
     * @return 驼峰命名法的字符串
     */
    public static String toCamelCase(String snakeCase) {
        if (snakeCase == null || snakeCase.isEmpty()) {
            return snakeCase;
        }

        StringBuilder camelCase = new StringBuilder();
        boolean nextUpperCase = false;
        for (char c : snakeCase.toCharArray()) {
            if (c == '_') {
                nextUpperCase = true; // 下一个字符应该大写
            } else if (nextUpperCase) {
                camelCase.append(Character.toUpperCase(c));
                nextUpperCase = false;
            } else {
                camelCase.append(c);
            }
        }

        // 如果第一个字符是大写的，转换为小写
        if (Character.isUpperCase(camelCase.charAt(0))) {
            camelCase.setCharAt(0, Character.toLowerCase(camelCase.charAt(0)));
        }

        return camelCase.toString();
    }

    /**
     * 将下划线分隔的字符串转换为帕斯卡命名法。
     * 例如：user_name -> UserName
     * @param snakeCase 下划线分隔的字符串
     * @return 帕斯卡命名法的字符串
     */
    public static String toPascalCase(String snakeCase) {
        if (snakeCase == null || snakeCase.isEmpty()) {
            return snakeCase;
        }

        StringBuilder pascalCase = new StringBuilder();
        boolean nextUpperCase = true; // 第一个字符应该是大写
        for (char c : snakeCase.toCharArray()) {
            if (c == '_') {
                nextUpperCase = true; // 下一个字符应该大写
            } else if (nextUpperCase) {
                pascalCase.append(Character.toUpperCase(c));
                nextUpperCase = false;
            } else {
                pascalCase.append(c);
            }
        }

        return pascalCase.toString();
    }

}
