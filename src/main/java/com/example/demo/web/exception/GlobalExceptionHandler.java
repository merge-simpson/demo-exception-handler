package com.example.demo.web.exception;

import com.example.demo.web.exception.status2xx.NoContentException;
import com.example.demo.web.support.exception.ApiError;
import com.example.demo.web.support.exception.ApiError.ApiSubError;
import com.example.demo.web.support.exception.CustomException;
import com.example.demo.web.support.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public final class GlobalExceptionHandler {
    // 따로 더 구체적인 선언이 없다면, CustomException을 상속받은 모든 예외가 이곳으로 온다. 따라서 이 양식을 따르는 한 따로 더 만들 익셉션 핸들러 메서드가 없다.
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiError> handleMemberException(CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        HttpStatus httpStatus = errorCode.defaultHttpStatus();
        ApiError response = ApiError.of(errorCode, getPlattedApiSubErrors(exception.getCause()));

        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<?> handleNoContentException(NoContentException exception) {
        return ResponseEntity.noContent().build();
    }

    private ApiSubError[] getPlattedApiSubErrors(Throwable cause) {
        Throwable currentCause = cause;
        List<ApiSubError> subErrors = new ArrayList<>();

        while (currentCause != null) {
            String errorFullName = currentCause.getClass().getSimpleName();
            String field = errorFullName.substring(errorFullName.lastIndexOf('.') + 1);
            subErrors.add(
                    ApiSubError.builder()
                            .field(field)
                            .message(currentCause.getLocalizedMessage())
                            .build()
            );
            currentCause = currentCause.getCause();
        }

        ApiSubError[] subErrorArray = new ApiSubError[subErrors.size()];
        return subErrors.toArray(subErrorArray);
    }
}
