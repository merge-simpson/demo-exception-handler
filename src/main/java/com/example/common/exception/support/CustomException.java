package com.example.common.exception.support;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class CustomException extends RuntimeException {

    protected final ErrorCode errorCode;
    protected final Runnable onError;
    protected final Supplier<Map<String, Object>> payloadSupplier;

    public CustomException() {
        super(getDefaultErrorCode().message());
        this.errorCode = getDefaultErrorCode();
        this.onError = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(String message) {
        super(message);
        this.errorCode = getDefaultErrorCode();
        this.onError = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = getDefaultErrorCode();
        this.onError = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.onError = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
        this.onError = () -> {};
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(ErrorCode errorCode, Runnable onError) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.onError = onError;
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(ErrorCode errorCode, Runnable onError, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
        this.onError = onError;
        this.payloadSupplier = Collections::emptyMap;
    }

    public CustomException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode.message());
        this.errorCode = errorCode;
        this.onError = () -> {};
        this.payloadSupplier = payloadSupplier;
    }

    public CustomException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
        this.onError = () -> {};
        this.payloadSupplier = payloadSupplier;
    }

    private static ErrorCode getDefaultErrorCode() {
        return DefaultErrorCodeHolder.DEFAULT_ERROR_CODE;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void executeOnError() {
        onError.run();
    }

    public Map<String, Object> getPayload() {
        return payloadSupplier.get();
    }

    public Map<String, Object> getPayloadOrElse(Map<String, Object> defaultPayload) {
        Objects.requireNonNull(defaultPayload, "defaultPayload must not be null");

        var payload = getPayload();
        return payload != null
                ? payload
                : defaultPayload;
    }

    public Map<String, Object> getPayloadOrElseGet(Supplier<Map<String, Object>> defaultPayloadSupplier) {
        Objects.requireNonNull(defaultPayloadSupplier, "defaultPayloadSupplier must not be null");

        var payload = getPayload();
        return payload != null
                ? payload
                : defaultPayloadSupplier.get();
    }

    private static class DefaultErrorCodeHolder { // 사용할 때 로드 + 스레드 세이프(클래스 로드 타임은 동시성 보장됨.)
        private static final ErrorCode DEFAULT_ERROR_CODE = new ErrorCode() {
            @Override
            public String name() {
                return "SERVER_ERROR";
            }

            @Override
            public HttpStatus httpStatus() {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public String message() {
                return "서버 오류";
            }

            @Override
            public CustomException exception() {
                return new CustomException(this);
            }

            @Override
            public CustomException exception(Throwable cause) {
                return new CustomException(this, cause);
            }

            @Override
            public RuntimeException exception(Runnable onError) {
                return new CustomException(this, onError);
            }

            @Override
            public RuntimeException exception(Runnable onError, Throwable cause) {
                return new CustomException(this, onError, cause);
            }

            @Override
            public RuntimeException exception(Supplier<Map<String, Object>> payloadSupplier) {
                return new CustomException(this, payloadSupplier);
            }

            @Override
            public RuntimeException exception(Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
                return new CustomException(this, payloadSupplier, cause);
            }
        };
    }
}
