package com.remo.common.exception.exception;

/**
 * 業務異常
 *
 * @author Jules
 * @date 2019/9/16
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
