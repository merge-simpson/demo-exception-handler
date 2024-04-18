package com.example.demo.common.support.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name(); // automatically overridden in enum
    String defaultMessage();
    HttpStatus defaultHttpStatus();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
}
