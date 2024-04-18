package com.example.demo.common.support.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiError(
        // Fields: 이곳에 작성한 것들이 멤버이자, 생성자 순서에 해당.
        String code,
        Integer status,
        String name,
        String message,
        @JsonInclude(Include.NON_EMPTY) List<ApiSubError> subErrors,
        LocalDateTime timestamp
) {
    public ApiError { // <-- 소괄호 없는 생성자는:
        // 생성자가 쓰일 때, 생성자의 파라미터에 대한 검토와 변경을 수행하는 공간.
        if (code == null) code = "API_ERROR";
        if (status == null) status = 500;
        if (name == null) name = "ApiError";
        if (message == null || "".equals(message)) message = "API 사용 중 서버에서 오류가 발생했습니다.";
        if (timestamp == null) timestamp = LocalDateTime.now();
    }

    @Builder
    public record ApiSubError(String field, String message) {}

    public static ApiError of(HttpStatus httpStatus) {
        return of(httpStatus, new ApiSubError[0]);
    }

    public static ApiError of(HttpStatus httpStatus, ApiSubError... subError) {
        return of(httpStatus, List.of(subError));
    }

    public static ApiError of(HttpStatus httpStatus, List<ApiSubError> subErrors) {

        return ApiError.builder()
                .code(httpStatus.name())
                .status(httpStatus.value())
                .name(httpStatus.getReasonPhrase())
                .message(httpStatus.series().name())
                .subErrors(subErrors)
                .build();
    }

    public static ApiError of(ErrorCode errorCode) {
        return of(errorCode, new ApiSubError[0]);
    }

    public static ApiError of(ErrorCode errorCode, ApiSubError... subError) {
        List<ApiSubError> subErrors = List.of(subError);
        return of(errorCode, subErrors);
    }

    public static ApiError of(ErrorCode errorCode, List<ApiSubError> subErrors) {
        String errorName = errorCode.defaultException().getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        return ApiError.builder()
                .code(errorCode.name())
                .status(errorCode.defaultHttpStatus().value())
                .name(errorName)
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
