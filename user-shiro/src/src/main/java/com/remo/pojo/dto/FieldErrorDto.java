package com.remo.pojo.dto;

import lombok.Data;

/**
 * 错误字段业务类
 *
 * @author Jules
 * @date 2019/6/25
 */
@Data
public class FieldErrorDto {
    private String fieldName;
    private String errMsg;

    public FieldErrorDto(){
    }

    public FieldErrorDto(String fieldName, String errMsg){
        this.fieldName = fieldName;
        this.errMsg = errMsg;
    }
}
