package com.example.demo.auth.exception;

import com.example.common.exception.support.CustomException;
import com.example.common.exception.support.ErrorCode;

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
}
