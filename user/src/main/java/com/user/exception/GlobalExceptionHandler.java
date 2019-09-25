package com.user.exception;

import com.user.exception.exception.BusinessException;
import com.user.pojo.vo.ResponseVo;
import com.user.util.ResponseUtil;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public ResponseVo exceptionShiRoHandler(ShiroException e){
        return ResponseUtil.initSuccessResultVO("Authorization failure");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    public ResponseVo exceptionGlobalHandler(BusinessException e){
        return ResponseUtil.initSuccessResultVO(e.getErrMsg());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseVo globalException(Exception e) {
        return ResponseUtil.initSuccessResultVO(e.getMessage());
    }
}

