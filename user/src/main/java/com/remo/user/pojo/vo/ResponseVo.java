package com.remo.user.pojo.vo;

/**
 * 视图默认返回对象
 */
public class ResponseVo {
    private Integer respCode;

    private String errMsg;

    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
