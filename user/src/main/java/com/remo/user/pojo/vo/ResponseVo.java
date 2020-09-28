package com.remo.user.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 视图默认返回对象
 */
@ApiModel(description = "View Object")
public class ResponseVo {
    @ApiModelProperty(required = true)
    private Integer respCode;

    @ApiModelProperty(notes = "only return when respCode=BusinessConstants.ERROR_RESULT_CODE")
    private String errMsg;

    @ApiModelProperty(notes = "only return when respCode=BusinessConstants.SUCCESS_RESULT_CODE")
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
