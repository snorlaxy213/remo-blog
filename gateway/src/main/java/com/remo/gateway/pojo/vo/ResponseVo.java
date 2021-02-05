package com.remo.gateway.pojo.vo;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
