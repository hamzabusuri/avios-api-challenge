package com.service.parentalcontrol.hamza.exception;

public class TechnicalFailureException extends Exception {
    public TechnicalFailureException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}