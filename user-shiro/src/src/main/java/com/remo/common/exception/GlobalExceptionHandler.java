package com.remo.common.exception;

import com.remo.common.exception.exception.BusinessException;
import com.remo.pojo.vo.ResponseVo;
import com.remo.util.ResponseUtil;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public ResponseVo exceptionShiRoHandler(ShiroException e){
        return ResponseUtil.initSuccessResultVO("Authorization failure");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    public ResponseVo exceptionGlobalHandler(BusinessException e){
        return ResponseUtil.initSuccessResultVO(e.getErrMsg());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseVo globalException(Exception e) {
        return ResponseUtil.initSuccessResultVO(e.getMessage());
    }
}

