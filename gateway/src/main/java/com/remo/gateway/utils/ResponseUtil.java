package com.remo.gateway.utils;

import com.remo.gateway.pojo.vo.ResponseVo;

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
}
