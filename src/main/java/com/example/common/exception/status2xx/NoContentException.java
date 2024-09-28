package com.example.common.exception.status2xx;

/**
 * This exception would provide the status of 204 NO_CONTENT. (not an error response)
 * It can be used to escape a routine using Java exception in some specific logic.
 */
public class NoContentException extends RuntimeException {
    public NoContentException() {}

    public NoContentException(String message) {
        super(message);
    }

    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
