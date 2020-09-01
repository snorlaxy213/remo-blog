package com.remo.article.controller.exception;

import com.remo.article.pojo.dto.FieldErrorDto;

import java.util.List;

public class ParamException extends RuntimeException {
    private boolean isFieldError;
    private List<FieldErrorDto> fieldErrorDtos;

    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, boolean isFieldError, List<FieldErrorDto> fieldErrorDtos) {
        super(message);
        this.isFieldError = isFieldError;
        this.fieldErrorDtos = fieldErrorDtos;
    }

    public ParamException(List<FieldErrorDto> fieldErrorDtos) {
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
