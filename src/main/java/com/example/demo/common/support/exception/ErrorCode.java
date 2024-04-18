package com.example.demo.common.support.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();
    HttpStatus defaultHttpStatus();
    String defaultMessage();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
}
