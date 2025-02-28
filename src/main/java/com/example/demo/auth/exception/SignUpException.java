package com.example.demo.auth.exception;

import com.example.common.exception.support.CustomException;
import com.example.common.exception.support.ErrorCode;

import java.util.Map;
import java.util.function.Supplier;

public class SignUpException extends CustomException {
    // intellij: Ctrl + O (generate constructors)
    public SignUpException() {
        super();
    }

    public SignUpException(String message) {
        super(message);
    }

    public SignUpException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignUpException(ErrorCode errorCode) {
        super(errorCode);
    }

    public SignUpException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public SignUpException(ErrorCode errorCode, Runnable onError) {
        super(errorCode, onError);
    }

    public SignUpException(ErrorCode errorCode, Runnable onError, Throwable cause) {
        super(errorCode, onError, cause);
    }

    public SignUpException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier) {
        super(errorCode, payloadSupplier);
    }

    public SignUpException(ErrorCode errorCode, Supplier<Map<String, Object>> payloadSupplier, Throwable cause) {
        super(errorCode, payloadSupplier, cause);
    }
}
