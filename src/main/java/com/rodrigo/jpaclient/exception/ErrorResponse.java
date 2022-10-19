package com.rodrigo.jpaclient.exception;

import lombok.Data;

import java.util.UUID;

@Data
public class ErrorResponse {
    private String requestId;
    private String error;
    private String description;

    public ErrorResponse(String error, String description) {
        this.requestId = String.valueOf(UUID.randomUUID());
        this.error = error;
        this.description = description;
    }
}
