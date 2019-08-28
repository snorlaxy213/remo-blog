package com.article.utils;

import com.article.pojo.vo.ResponseVo;

/**
 * ResponseVo 工具类
 *
 * @author Jules
 * @date 2019/8/28
 */
public class ResponseUtil {
    public static ResponseVo initSuccessResponse(Object object) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(0);
        responseVo.setContent(object);
        return responseVo;
    }

    public static ResponseVo initFailResponse(String message) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(1);
        responseVo.setMessage(message);
        return responseVo;
    }
}
