package com.example.stepbackend.global.advice;

import com.example.stepbackend.global.common.response.api.ApiResponse;
import com.example.stepbackend.global.common.response.error.ErrorResponse;
import com.example.stepbackend.global.common.response.error.ErrorResponseBody;
import com.example.stepbackend.global.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody()
                .setCode(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
                .setClasses(ex.getClass().getSimpleName());

        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.METHOD_NOT_ALLOWED.value())
                .setMessage(ex.getLocalizedMessage())
                .setTimestamp(LocalDateTime.now());


        ErrorResponse errorResponse = new ErrorResponse()
                .setApiResponse(apiResponse)
                .setResult(errorResponseBody);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorResponse> handleBadRequestException(
            MissingRequestHeaderException ex) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody()
                .setCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setClasses(ex.getClass().getSimpleName());

        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setMessage(ex.getLocalizedMessage())
                .setTimestamp(LocalDateTime.now());


        ErrorResponse errorResponse = new ErrorResponse()
                .setApiResponse(apiResponse)
                .setResult(errorResponseBody);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(
            MissingRequestHeaderException ex) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody()
                .setCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setClasses(ex.getClass().getSimpleName());

        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setMessage(ex.getLocalizedMessage())
                .setTimestamp(LocalDateTime.now());


        ErrorResponse errorResponse = new ErrorResponse()
                .setApiResponse(apiResponse)
                .setResult(errorResponseBody);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ErrorResponse> handleNoHandleFoundException(Exception ex) {
        ErrorResponseBody errorResponseBody = new ErrorResponseBody()
                .setCode(HttpStatus.NOT_FOUND.getReasonPhrase())
                .setClasses(ex.getClass().getSimpleName());


        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage(ex.getLocalizedMessage())
                .setTimestamp(LocalDateTime.now());

        ErrorResponse errorResponse = new ErrorResponse()
                .setApiResponse(apiResponse)
                .setResult(errorResponseBody);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception ex) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody()
                .setCode(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .setClasses(ex.getClass().getSimpleName());

        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setMessage(ex.getLocalizedMessage())
                .setTimestamp(LocalDateTime.now());


        ErrorResponse errorResponse = new ErrorResponse()
                .setApiResponse(apiResponse)
                .setResult(errorResponseBody);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception ex) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody()
                .setCode(HttpStatus.FORBIDDEN.getReasonPhrase())
                .setClasses(ex.getClass().getSimpleName());


        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.FORBIDDEN.value())
                .setMessage("API에 접근할 권한이 없습니다.")
                .setTimestamp(LocalDateTime.now());

        ErrorResponse errorResponse = new ErrorResponse()
                .setApiResponse(apiResponse)
                .setResult(errorResponseBody);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ErrorResponse> handleUrlNullPointException(Exception ex) {


        ErrorResponseBody errorResponseBody = new ErrorResponseBody()
                .setCode(HttpStatus.NOT_FOUND.getReasonPhrase())
                .setClasses(ex.getClass().getSimpleName());

        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage(ex.getLocalizedMessage())
                .setTimestamp(LocalDateTime.now());

        ErrorResponse errorResponse = new ErrorResponse()
                .setApiResponse(apiResponse)
                .setResult(errorResponseBody);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
