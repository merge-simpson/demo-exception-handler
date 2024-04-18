package com.example.demo.common.support.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    // ===== statics =====
    private static ErrorCode getDefaultErrorCode() {
        return DefaultErrorCodeHolder.DEFAULT_ERROR_CODE;
    }

    // ===== non-static fields =====
    protected ErrorCode ERROR_CODE;

    // ===== Constructors =====
    public CustomException() {
        this.ERROR_CODE = getDefaultErrorCode();
    }

    public CustomException(String message) {
        super(message);
        this.ERROR_CODE = getDefaultErrorCode();
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.ERROR_CODE = getDefaultErrorCode();
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.defaultMessage());
        this.ERROR_CODE = errorCode;
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.defaultMessage(), cause);
        this.ERROR_CODE = errorCode;
    }

    // ===== Non-static Methods =====
    public ErrorCode getErrorCode() {
        return ERROR_CODE;
    }

    // ===== Inner Classes =====
    private static class DefaultErrorCodeHolder { // 사용할 때 로드 + 스레드 세이프(클래스 로드 타임은 동시성 보장됨.)
        private static final ErrorCode DEFAULT_ERROR_CODE = new ErrorCode() {
            @Override
            public String name() {
                return "SERVER_ERROR";
            }

            @Override
            public HttpStatus defaultHttpStatus() {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public String defaultMessage() {
                return "서버 오류";
            }

            @Override
            public RuntimeException defaultException() {
                return new CustomException(this);
            }

            @Override
            public RuntimeException defaultException(Throwable cause) {
                return new CustomException(this, cause);
            }
        };
    }
}
