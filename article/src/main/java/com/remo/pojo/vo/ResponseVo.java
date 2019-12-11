package com.remo.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseVo {
    /**
     * 0 - success, 1 -fail
     */
    private int code;
    private String message;
    private Object content;

    public ResponseVo(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
