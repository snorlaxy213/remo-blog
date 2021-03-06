package com.remo.user.common.exception;

import com.remo.user.common.exception.exception.BusinessException;
import com.remo.user.pojo.vo.ResponseVo;
import com.remo.user.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    public ResponseVo exceptionGlobalHandler(BusinessException e) {
        return ResponseUtil.initSuccessResultVO(e.getErrMsg());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseVo globalException(Exception e) {
        return ResponseUtil.initSuccessResultVO(e.getMessage());
    }
}

