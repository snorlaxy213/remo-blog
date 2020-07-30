package com.remo.seckill.exception;

import com.remo.seckill.exception.exception.BusinessException;
import com.remo.seckill.exception.exception.ImageException;
import com.remo.seckill.pojo.vo.ResponseVo;
import com.remo.seckill.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ImageException.class)
    public ResponseVo exceptionGlobalHandler(ImageException e) {
        return ResponseUtil.initSuccessResultVO(e.getErrMsg());
    }

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

