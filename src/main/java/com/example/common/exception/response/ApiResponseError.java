package com.example.common.exception.response;

import com.example.common.exception.support.CustomException;
import com.example.common.exception.support.ErrorCode;
import com.example.common.util.text.TextCaseUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 *
 * @param code 에러 코드 명
 * @param status 상태 코드 값
 * @param name 오류 이름
 * @param message 오류 메시지
 * @param error HTTP 상태 코드 이름
 * @param cause
 * @param timestamp 발생 시각
 * @param path 오류가 발생한 경로
 * @param payload extension members
 */
@Builder
public record ApiResponseError(
        String code,
        Integer status,
        String name,
        String message,
        String error,
        @JsonInclude(Include.NON_EMPTY) List<ApiSimpleError> cause,
        Instant timestamp,
        @JsonInclude(Include.NON_NULL) String path,
        @JsonInclude(Include.NON_EMPTY) Map<String, Object> payload
) {
    public static ApiResponseError of(CustomException exception, String path) {
        return of(exception, path, null);
    }

    public static ApiResponseError of(CustomException exception) {
        return of(exception, null, null);
    }

    public static ApiResponseError of(CustomException exception, String path, Map<String, Object> payload) {
        ErrorCode errorCode = exception.getErrorCode();
        String errorName = exception.getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);
        String error = TextCaseUtil.capitalizeAndSaveUpperSnakeCase(
                errorCode.defaultHttpStatus().name()
        );

        return ApiResponseError.builder()
                .code(errorCode.name())
                .status(errorCode.defaultHttpStatus().value())
                .name(errorName)
                .message(exception.getMessage())
                .error(error)
                .cause(ApiSimpleError.listOfCauseSimpleError(exception))
                .path(path)
                .payload(payload)
                .build();
    }

    public ApiResponseError {
        if (code == null) {
            code = "API_ERROR";
        }

        if (status == null) {
            status = 500;
        }

        if (name == null) {
            name = "ApiError";
        }

        if (message == null || message.isBlank()) {
            message = "API 오류";
        }

        if (timestamp == null) {
            timestamp = Instant.now();
        }

        if (path == null) {
            path = "about:blank";
        }

        if (payload != null && payload.isEmpty()) {
            payload = null;
        }
    }
}