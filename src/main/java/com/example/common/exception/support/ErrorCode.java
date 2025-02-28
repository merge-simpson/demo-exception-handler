package com.example.common.exception.support;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public interface ErrorCode {
    String name(); // automatically overridden in enum
    String defaultMessage();
    HttpStatus defaultHttpStatus();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
    RuntimeException defaultException(Runnable onError);
    RuntimeException defaultException(Runnable onError, Throwable cause);
    RuntimeException defaultException(Supplier<Map<String, Object>> payloadSupplier);
    RuntimeException defaultException(Supplier<Map<String, Object>> payloadSupplier, Throwable cause);
}
