package com.user.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 视图默认返回对象
 */
@Data
public class ResponseVo {
    @ApiModelProperty(required = true)
    private Integer respCode;

    @ApiModelProperty(notes = "only return when respCode=BusinessConstants.ERROR_RESULT_CODE")
    private String errMsg;

    @ApiModelProperty(notes = "only return when respCode=BusinessConstants.SUCCESS_RESULT_CODE")
    private Object data;
}
