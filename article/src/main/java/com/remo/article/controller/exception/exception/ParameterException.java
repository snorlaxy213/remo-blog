package com.remo.article.controller.exception.exception;

import com.remo.article.pojo.dto.FieldErrorDto;

import java.util.List;

/**
 * Parameter Exception
 *
 * @author Jules
 * @date 2019/6/25
 */
public class ParameterException extends RuntimeException {
    private boolean isFieldError;
    private List<FieldErrorDto> fieldErrorDtos;

    public ParameterException() {
        super();
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, boolean isFieldError, List<FieldErrorDto> fieldErrorDtos) {
        super(message);
        this.isFieldError = isFieldError;
        this.fieldErrorDtos = fieldErrorDtos;
    }

    public boolean isFieldError() {
        return isFieldError;
    }

    public void setFieldError(boolean fieldError) {
        isFieldError = fieldError;
    }

    public List<FieldErrorDto> getFieldErrorDtos() {
        return fieldErrorDtos;
    }

    public void setFieldErrorDtos(List<FieldErrorDto> fieldErrorDtos) {
        this.fieldErrorDtos = fieldErrorDtos;
    }
}
