package com.example.demo.web.support.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiError(
        String code,
        Integer status,
        String name,
        String message,
        @JsonInclude(Include.NON_EMPTY) List<ApiSubError> subErrors,
        LocalDateTime timestamp
) {
    public ApiError {
        if (code == null) code = "API_ERROR";
        if (status == null) status = 500;
        if (name == null) name = "ApiError";
        if (message == null || "".equals(message)) message = "API 사용 중 서버에서 오류가 발생했습니다.";
        if (timestamp == null) timestamp = LocalDateTime.now();
    }

    public record ApiSubError(String field, String message) {}

    public static ApiError of(HttpStatus httpStatus) {
        return ApiError.builder()
                .code(httpStatus.name())
                .status(httpStatus.value())
                .name(httpStatus.getReasonPhrase())
                .message(httpStatus.series().name())
                .build();
    }

    public static ApiError of(HttpStatus httpStatus, ApiSubError... subError) {
        List<ApiSubError> subErrors = List.of(subError);

        return ApiError.builder()
                .code(httpStatus.name())
                .status(httpStatus.value())
                .name(httpStatus.getReasonPhrase())
                .message(httpStatus.series().name())
                .subErrors(subErrors)
                .build();
    }

    public static ApiError of(ErrorCode errorCode) {
        String errorName = errorCode.defaultException().getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        return ApiError.builder()
                .code(errorCode.name())
                .status(errorCode.defaultHttpStatus().value())
                .name(errorName)
                .message(errorCode.defaultMessage())
                .build();
    }

    public static ApiError of(ErrorCode errorCode, ApiSubError... subError) {
        List<ApiSubError> subErrors = List.of(subError);

        return ApiError.builder()
                .code(errorCode.name())
                .status(errorCode.defaultHttpStatus().value())
                .name(errorCode.defaultException().getClass().getName())
                .message(errorCode.defaultMessage())
                .subErrors(subErrors)
                .build();
    }

    public ApiError appendSubErrors(ApiSubError... subError) {
        return this.appendSubErrors(List.of(subError));
    }

    public ApiError appendSubErrors(List<ApiSubError> subErrors) {
        return ApiError.builder()
                .timestamp(this.timestamp())
                .status(this.status())
                .name(this.name())
                .message(this.message())
                .subErrors(subErrors)
                .build();
    }
}
