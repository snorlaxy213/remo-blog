package com.remo.seckill.exception.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Demo
 *
 * @author Vino
 * @date 2020/3/12
 */
@Getter
@Setter
@AllArgsConstructor
public class ImageException extends RuntimeException {
    private Integer respCode;
    private String errMsg;
}
