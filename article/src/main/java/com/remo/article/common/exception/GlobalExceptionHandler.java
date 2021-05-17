package com.remo.article.common.util.exception;

import com.remo.article.common.util.ResponseUtil;
import com.remo.article.common.util.exception.exception.ParameterException;
import com.remo.article.pojo.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ParameterException.class)
    public ResponseVo paramExceptionHandler(HttpServletRequest request, ParameterException e) {
        return ResponseUtil.initFailResponse(e.getFieldErrorDtos());
    }

}
