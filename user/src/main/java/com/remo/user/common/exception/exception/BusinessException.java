package com.remo.user.common.exception.exception;


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
