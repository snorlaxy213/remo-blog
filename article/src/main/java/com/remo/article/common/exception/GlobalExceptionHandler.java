package com.remo.article.common.exception;

import com.remo.article.common.exception.exception.ParamException;
import com.remo.article.common.utils.ResponseUtil;
import com.remo.article.pojo.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ParamException.class)
    public ResponseVo paramExceptionHandler(HttpServletRequest request, ParamException e) {
        System.out.println("参数校验错误");
        return ResponseUtil.initFailResponse(e.getFieldErrorDtos());
    }

}
