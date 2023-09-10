package com.example.stepbackend.global.exception.assertion;

import com.example.stepbackend.global.exception.InvalidParameterException;
import com.example.stepbackend.global.exception.ResourceNotFoundException;
import com.example.stepbackend.global.exception.UserNotFoundException;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;

import java.util.Optional;

public class ExceptionAssert extends Assert {

    public static void isVaildParameter(Errors errors) {
        if(errors.hasErrors()) {
            throw new InvalidParameterException(errors.getObjectName() + "파라미터에 잘못 된 값 요청", errors);
        }
    }

    public static void isUserExist(Object object) {
        if(object == null) {
            throw new UserNotFoundException("사용자가 존재하지 않습니다.");
        }
    }

    public static void isObjectNull(Object object) {
        if(object == null) {
            throw new ResourceNotFoundException("해당하는 값이 존재하지 않습니다.");
        }
    }

    public static void isOptionalPresent(Optional<?> value) {
        if(!value.isPresent()) {
            throw new ResourceNotFoundException("잘못 된 요청입니다.");
        }
    }
}
