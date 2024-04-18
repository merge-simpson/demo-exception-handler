package com.example.demo.common.support;


import com.example.demo.common.support.exception.ErrorCode;

import java.text.MessageFormat;
import java.util.Objects;

public final class Preconditions {
    public static <T> T notNull(T obj) { return Objects.requireNonNull(obj); }

    public static void require(boolean expression) {
        if(!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void require(boolean expression, String message) {
        if(!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void require(boolean expression, String message, Object... args) {
        if(!expression) {
            throw new IllegalArgumentException(MessageFormat.format(message, args));
        }
    }

    public static void check(boolean expression) {
        if(!expression) {
            throw new IllegalStateException();
        }
    }

    public static void check(boolean expression, String message) {
        if(!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void check(boolean expression, String message, Object... args) {
        if(!expression) {
            throw new IllegalStateException(MessageFormat.format(message, args));
        }
    }

    public static void validate(boolean condition, ErrorCode errorCode) {
        if(!condition) {
            throw errorCode.defaultException();
        }
    }

    public static void validate(boolean condition, ErrorCode errorCode, Throwable cause) {
        if(!condition) {
            throw errorCode.defaultException(cause);
        }
    }
}
