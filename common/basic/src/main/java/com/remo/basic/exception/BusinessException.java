package com.remo.basic.exception;

/**
 * 業務異常
 *
 * @author Vino
 * @date 2019/9/16
 */
public class BusinessException extends RuntimeException {

    private Integer respCode;
    private String errMsg;

    public BusinessException(Integer respCode, String errMsg) {
        this.respCode = respCode;
        this.errMsg = errMsg;
    }

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
