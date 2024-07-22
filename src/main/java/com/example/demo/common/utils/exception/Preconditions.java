package com.example.demo.common.utils.exception;

import com.example.demo.common.support.exception.ErrorCode;

public final class Preconditions {
    public static void validate(boolean condition, ErrorCode errorCode) {
        if (!condition) {
            throw errorCode.defaultException();
        }
    }
}