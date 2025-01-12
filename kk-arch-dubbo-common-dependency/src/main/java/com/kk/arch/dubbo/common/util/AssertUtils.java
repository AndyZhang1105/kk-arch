package com.kk.arch.dubbo.common.util;

import com.kk.arch.dubbo.remote.exception.BusinessException;

import java.util.List;
import java.util.Objects;

/**
 * @author Zal
 */
public class AssertUtils {

    public static int ERROR_CODE = -1;

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(ERROR_CODE, message);
        }
    }

    public static void isNotNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new BusinessException(ERROR_CODE, message);
        }
    }

    public static <T> void isNotEmpty(List<T> list, String message) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ERROR_CODE, message);
        }
    }

    public static void isNotEmpty(String params, String message) {
        if (params == null || params.isEmpty()) {
            throw new BusinessException(ERROR_CODE, message);
        }
    }

    public static void isNotBlank(String params, String message) {
        if (params == null || params.isBlank()) {
            throw new BusinessException(ERROR_CODE, message);
        }
    }

}
