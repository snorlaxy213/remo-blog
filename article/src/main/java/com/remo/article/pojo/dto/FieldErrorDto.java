package com.remo.article.pojo.dto;

/**
 * 错误字段实体类
 *
 * @author jules
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

