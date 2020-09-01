package com.remo.gateway.utils;

import com.remo.basic.constant.RemoConstants;
import com.remo.gateway.pojo.vo.ResponseVo;

/**
 * 视图对象工具类
 *
 * @author Jules
 * @date 2019/6/25
 */
public class ResponseUtil {
    public static ResponseVo initSuccessResultVO() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstants.SUCCESS_RESULT_CODE);
        return responseVo;
    }

    public static ResponseVo initSuccessResultVO(Object data) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstants.SUCCESS_RESULT_CODE);
        responseVo.setData(data);
        return responseVo;
    }

    public static ResponseVo initErrorResultVO() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstants.ERROR_RESULT_CODE);
        return responseVo;
    }

    public static ResponseVo initErrorResultVO(String msg) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setRespCode(RemoConstants.ERROR_RESULT_CODE);
        responseVo.setErrMsg(msg);
        return responseVo;
    }
}
