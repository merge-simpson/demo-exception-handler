package com.example.common.exception.response;

import com.example.common.exception.support.CustomException;
import com.example.common.util.text.TextCaseUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 *
 * 실제 프로젝트에 적용 시 하기 항목 중 필요한 것만 고르는 것도 편리한 구현 전략 중 하나입니다.
 * <br />
 *
 * <blockquote>
 *     <p>하기 항목을 고려해 각 프로젝트에서 원하는 수준으로 리팩토링 하세요.</p>
 *     <ul>
 *         <li>
 *             {@code @JsonInclude(...)}는 이 영역에 Jackson 의존성을 침투시킵니다.
 *             라이브러리 모듈화 시 Jackson 의존성을 {@code compileOnly("...")}로 제공하면
 *             consumer 모듈에서 이 의존성을 회피할 수 있습니다.
 *         </li>
 *     </ul>
 * </blockquote>
 *
 * @param code 에러 코드 명
 * @param status 상태 코드 값
 * @param name 오류 이름
 * @param message 오류 메시지
 * @param error HTTP 상태 코드 이름
 * @param cause 이 오류를 야기한 오류
 * @param timestamp 발생 시각
 * @param path 오류가 발생한 경로
 * @param payload extension members
 */
@Builder
public record ApiErrorResponse(
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
    public static ApiErrorResponse of(CustomException exception, String path, Map<String, Object> payload) {
        var errorCode = exception.getErrorCode();
        var errorName = exception.getClass().getName();
        var error = TextCaseUtil.capitalizeAndSaveUpperSnakeCase(
                errorCode.httpStatus().name()
        );
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        return ApiErrorResponse.builder()
                .code(errorCode.name())
                .status(errorCode.httpStatus().value())
                .name(errorName)
                .message(exception.getMessage())
                .error(error)
                .cause(ApiSimpleError.listOfCauseSimpleError(exception))
                .path(path)
                .payload(payload)
                .build();
    }

    public ApiErrorResponse {
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