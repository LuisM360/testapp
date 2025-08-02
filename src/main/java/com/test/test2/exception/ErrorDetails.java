package com.test.test2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details; // URI path where error occurred
    private String errorCode; // A custom code for the type of error (e.g., "RESOURCE_NOT_FOUND", "VALIDATION_FAILED")
    private Map<String, String> validationErrors; // Optional: for field-specific validation errors

    // Constructor for errors without specific validation details
    public ErrorDetails(LocalDateTime timestamp, String message, String details, String errorCode) {
        this(timestamp, message, details, errorCode, null);
    }
}