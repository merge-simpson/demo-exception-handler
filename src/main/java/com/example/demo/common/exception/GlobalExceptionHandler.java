package com.example.demo.common.exception;

import com.example.demo.common.exception.status2xx.NoContentException;
import com.example.demo.common.support.exception.CustomException;
import com.example.demo.common.support.exception.response.ApiResponseError;
import com.example.demo.common.support.exception.response.ApiSimpleError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public final class GlobalExceptionHandler {
    // 따로 더 구체적인 선언이 없다면, CustomException을 상속받은 모든 예외가 이곳으로 온다. 따라서 이 양식을 따르는 한 따로 더 만들 익셉션 핸들러 메서드가 없다.
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseError> handleMemberException(CustomException exception) {
        ApiResponseError response = ApiResponseError.of(exception);
        HttpStatus httpStatus = exception
                .getErrorCode()
                .defaultHttpStatus();

        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * validation 애노테이션(유효성) 예외를 최종적으로 이곳에서 처리할 수 있음.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseError> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldError();

        ApiResponseError response = ApiResponseError.builder()
                .name(exception.getBody().getTitle())
                .message(
                        fieldError == null ? exception.getMessage() : fieldError.getDefaultMessage()
                )
                .status(exception.getStatusCode().value())
                .timestamp(Instant.now())
                .cause(ApiSimpleError.listOfCauseSimpleError(exception.getCause()))
                .build();

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(response);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<?> handleNoContentException(NoContentException exception) {
        return ResponseEntity.noContent().build();
    }
}
