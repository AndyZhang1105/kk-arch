package com.kk.arch.util;

import com.kk.arch.exception.BusinessException;

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


}
