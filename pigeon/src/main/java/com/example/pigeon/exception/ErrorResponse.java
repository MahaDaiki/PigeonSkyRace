package com.example.pigeon.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private int status;

    public ErrorResponse(LocalDateTime timestamp,int status , String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
