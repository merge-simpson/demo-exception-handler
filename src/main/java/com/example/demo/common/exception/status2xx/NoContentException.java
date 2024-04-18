package com.example.demo.common.exception.status2xx;

public class NoContentException extends RuntimeException {
    public NoContentException() {}

    public NoContentException(String message) {
        super(message);
    }

    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
