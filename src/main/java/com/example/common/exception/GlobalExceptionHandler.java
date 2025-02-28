package com.example.common.exception;

import com.example.common.exception.status2xx.NoContentException;
import com.example.common.exception.support.CustomException;
import com.example.common.exception.response.ApiErrorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public final class GlobalExceptionHandler {

    /** CustomException 핸들링 예시
     *  더 구체적인 예외를 핸들링하는 메서드가 없다면 이곳에서 핸들링한다.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomException(
            CustomException exception,
            HttpServletRequest request
    ) {
        String path = request.getRequestURI();
        HttpStatus httpStatus = exception
                .getErrorCode()
                .httpStatus();

        exception.executeOnError();
        var payload = exception.getPayload();

        ApiErrorResponse response = ApiErrorResponse.of(exception, path, payload);

        return ResponseEntity
                .status(httpStatus)
                .body(response);
    }

    /**
     * validation 애노테이션(유효성) 예외를 최종적으로 이곳에서 처리할 수 있음.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        BindingResult bindingResult = exception.getBindingResult();
        record Response(
                Instant timestamp,
                int status,
                String error,
                @JsonInclude(Include.NON_NULL)
                String message,
                String path
        ) {}

        @SuppressWarnings("ConstantConditions")
        String message = bindingResult.hasFieldErrors() ?
                bindingResult.getFieldError().getDefaultMessage()
                : null;

        Response body = new Response(
                Instant.now(),
                exception.getStatusCode().value(),
                exception.getStatusCode().toString(),
                message,
                request.getRequestURI()
        );

        return ResponseEntity
                .badRequest()
                .body(body);
    }

    /**
     * This exception would provide the status of 204 NO_CONTENT. (not an error response)
     * It can be used to escape a routine using Java exception in some specific logic.
     */
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<?> handleNoContentException(NoContentException ignoredException) {
        return ResponseEntity.noContent().build();
    }
}
