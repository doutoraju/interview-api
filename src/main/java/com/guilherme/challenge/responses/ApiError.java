package com.guilherme.challenge.responses;

import java.util.ArrayList;
import java.util.List;

public class ApiError {
	
	private List<String> errors;
	private String exceptionDetails;
	
	public List<String> getErrors() {
		if(this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public String getExceptionDetails() {
		return exceptionDetails;
	}

	public void setExceptionDetails(String data) {
		this.exceptionDetails = data;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
