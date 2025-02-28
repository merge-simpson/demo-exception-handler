package com.example.common.exception.support;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public interface ErrorCode {
    String name(); // automatically overridden in enum
    String message();
    HttpStatus httpStatus();
    RuntimeException exception();
    RuntimeException exception(Throwable cause);
    RuntimeException exception(Runnable onError);
    RuntimeException exception(Runnable onError, Throwable cause);
    RuntimeException exception(Supplier<Map<String, Object>> payloadSupplier);
    RuntimeException exception(Supplier<Map<String, Object>> payloadSupplier, Throwable cause);
}
