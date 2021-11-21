package com.example.devicemanagement.common;

import lombok.Data;

@Data
public class ApiException extends RuntimeException {

    private String details;

    public ApiException(String details, String message) {
        super(message);
        this.details = details;
    }

    public ApiException(String message) {
        super(message);
    }
}
