package com.remo.userShiro.utils;

import com.remo.userShiro.common.domain.RemoConstant;
import com.remo.userShiro.pojo.dto.FieldErrorDto;
import com.remo.userShiro.pojo.vo.ResponseVo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * 视图对象工具类
 *
 * @author Jules
 * @date 2019/6/25
 */
public class ResponseUtil {
    public static ResponseVo initSuccessResultVO() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstant.SUCCESS_RESULT_CODE);
        return responseVo;
    }

    public static ResponseVo initSuccessResultVO(Object data) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstant.SUCCESS_RESULT_CODE);
        responseVo.setData(data);
        return responseVo;
    }

    public static ResponseVo initErrorResultVO() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstant.ERROR_RESULT_CODE);
        return responseVo;
    }

    public static ResponseVo initErrorResultVO(String msg) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstant.ERROR_RESULT_CODE);
        responseVo.setErrMsg(msg);
        return responseVo;
    }

    public static ResponseVo initErrorResultVO(BindingResult bindingResult, String errMsg) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstant.ERROR_RESULT_CODE);
        responseVo.setErrMsg(errMsg);
        List<FieldErrorDto> list = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> {
            FieldErrorDto dto = new FieldErrorDto();
            FieldError fieldError = (FieldError) error;
            dto.setFieldName(fieldError.getField());
            dto.setErrMsg(error.getDefaultMessage());
            list.add(dto);
        });
        responseVo.setData(list);

        return responseVo;
    }

    public static ResponseVo initErrorResultVO(List<FieldErrorDto> fieldErrorDtos, String errMsg) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstant.ERROR_RESULT_CODE);
        responseVo.setErrMsg(errMsg);
        responseVo.setData(fieldErrorDtos);
        return responseVo;
    }
}
