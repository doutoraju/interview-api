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
		handleValidationExpections();
	}

	public BindingResult getBindingResults() {

		return results;
	}

	private void handleValidationExpections() {
		if (getCause() != null && (getCause() instanceof BindException)) {
			results = ((BindException) getCause()).getBindingResult();
		}
	}
}
