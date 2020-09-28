package com.remo.user.pojo.dto;

/**
 * 错误字段业务类
 *
 * @author Jules
 * @date 2019/6/25
 */
public class FieldErrorDto {
    private String fieldName;
    private String errMsg;

    public FieldErrorDto() {
    }

    public FieldErrorDto(String fieldName, String errMsg) {
        this.fieldName = fieldName;
        this.errMsg = errMsg;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
