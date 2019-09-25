package com.user.exception.exception;

import com.user.pojo.dto.FieldErrorDto;
import lombok.Data;

import java.util.List;

/**
 * User Parameter Exception
 *
 * @author Jules
 * @date 2019/6/25
 */
@Data
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
}
