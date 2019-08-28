package com.article.pojo.vo;

import lombok.Data;

@Data
public class ResponseVo {
    /**
     * 0 - success, 1 -fail
     */
    private int code;
    private String message;
    private Object content;

    public ResponseVo() {
    }

    public ResponseVo(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
