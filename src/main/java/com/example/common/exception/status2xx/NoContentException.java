package com.example.common.exception.status2xx;

/**
 * Not for error response, but for exceptional logic for 204 NO_CONTENT.
 *  Optional responsive data set sometimes requires empty response body and this response status.
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
