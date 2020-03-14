package com.remo.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 视图默认返回对象
 */
@Setter
@Getter
public class ResponseVo {
    private Integer respCode;

    private String errMsg;

    private Object data;
}
