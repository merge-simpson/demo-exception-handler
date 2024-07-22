package com.example.common.exception.support;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name(); // automatically overridden in enum
    String defaultMessage();
    HttpStatus defaultHttpStatus();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
}
