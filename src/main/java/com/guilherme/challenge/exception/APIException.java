package com.guilherme.challenge.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class APIException extends Exception {

    private BindingResult results;

    public APIException(Exception ex) {
        this(null, ex);
    }

    public APIException(String message) {
        this(message, null);
    }

    public APIException(String message, Exception ex) {
        super(message, ex);
        handleValidationExceptions();
    }

    public BindingResult getBindingResult() {
        return results;
    }

    private void handleValidationExceptions() {
        if (getCause() != null && (getCause() instanceof BindException)) {
            results = ((BindException) getCause()).getBindingResult();
        }
    }
}
