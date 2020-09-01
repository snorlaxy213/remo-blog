package com.remo.article.common.util;

import com.remo.article.pojo.dto.FieldErrorDto;
import com.remo.article.pojo.vo.ResponseVo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * ResponseVo 工具类
 *
 * @author Jules
 * @date 2019/8/28
 */
public class ResponseUtil {

    private static final int successCode = 0;
    private static final int errorCode = 1;

    public static ResponseVo initSuccessResponse(Object object) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(successCode);
        responseVo.setContent(object);
        return responseVo;
    }

    public static ResponseVo initFailResponse(Object object) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(errorCode);
        responseVo.setContent(object);
        return responseVo;
    }

    public static ResponseVo initFailResponse(String message) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(errorCode);
        responseVo.setMessage(message);
        return responseVo;
    }

    public static ResponseVo initFailResponse(BindingResult bindingResult) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(errorCode);
        List<FieldErrorDto> list = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> {
            FieldErrorDto dto = new FieldErrorDto();
            FieldError fieldError = (FieldError) error;
            dto.setFieldName(fieldError.getField());
            dto.setErrMsg(error.getDefaultMessage());
            list.add(dto);
        });
        responseVo.setContent(list);
        return responseVo;
    }
}
