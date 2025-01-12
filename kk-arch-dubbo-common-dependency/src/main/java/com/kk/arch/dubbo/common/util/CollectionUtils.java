package com.kk.arch.dubbo.common.util;

import java.util.List;

/**
 * @author Zal
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static <T> boolean isNotEmpty(List<T> paramList) {
        return !org.springframework.util.CollectionUtils.isEmpty(paramList);
    }

    public static boolean anyMatch(List<String> aList, List<String> bList) {
        return aList.stream().anyMatch(bList::contains);
    }

    public static boolean noneMatch(List<String> aList, List<String> bList) {
        return aList.stream().noneMatch(bList::contains);
    }

}
