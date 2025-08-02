package com.test.test2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to indicate that a requested resource was not found.
 * Maps to HTTP 404 NOT FOUND.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // This annotation ensures Spring returns a 404 status
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
