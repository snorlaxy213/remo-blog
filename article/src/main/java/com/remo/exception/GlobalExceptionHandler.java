package com.remo.exception;

import com.remo.exception.exception.ParamException;
import com.remo.pojo.vo.ResponseVo;
import com.remo.utils.ResponseUtil;
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
