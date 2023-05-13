package com.example.demo.web.support.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getName();
    HttpStatus defaultHttpStatus();
    String defaultMessage();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
}
