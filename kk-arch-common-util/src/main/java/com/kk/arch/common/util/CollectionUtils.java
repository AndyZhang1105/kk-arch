package com.kk.arch.common.util;

import java.util.List;

/**
 * @author Zal
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static <T> boolean isNotEmpty(List<T> paramList) {
        return !org.springframework.util.CollectionUtils.isEmpty(paramList);
    }

}
