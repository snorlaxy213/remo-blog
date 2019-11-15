package com.remo.exception.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private Integer respCode;
    private String errMsg;

    public BusinessException(Integer respCode,String errMsg) {
        this.respCode = respCode;
        this.errMsg = errMsg;
    }
}
