package com.user.exception.exception;

import lombok.Data;

/**
 * User Business Exception
 *
 * @author Jules
 * @date 2019/6/26
 */
@Data
public class BusinessException extends RuntimeException {
    private Integer respCode;
    private String errMsg;

    public BusinessException(Integer respCode,String errMsg) {
        this.respCode = respCode;
        this.errMsg = errMsg;
    }
}
