package com.kk.arch.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Zal
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class BusinessException extends RuntimeException implements Serializable {

    private final int code;
    private final String msg;

    public BusinessException(int code, String msg) {
        super(msg);

        this.code = code;
        this.msg = msg;
    }

}
