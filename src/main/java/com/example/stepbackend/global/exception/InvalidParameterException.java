package com.example.stepbackend.global.exception;

import com.example.stepbackend.global.exception.BadRequestException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;

public class InvalidParameterException extends BadRequestException {

    private final Errors errors;
    public InvalidParameterException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public List<FieldError> getFieldErrors() {
        return errors.getFieldErrors();
    }
}

