package com.guilherme.challenge.controllers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.guilherme.challenge.exception.APIException;
import com.guilherme.challenge.responses.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class InterviewExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NumberFormatException.class)
	protected ResponseEntity<ApiError> handlebrFormatException(NumberFormatException ex) {
		ApiError apiError = new ApiError();
		apiError.setExceptionDetails(ex.getMessage());
		apiError.getErrors().add("The ID provided is not a valid number.");
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<ApiError> handleIllegaArgs(IllegalArgumentException ex) {
		ApiError apiError = new ApiError();
		apiError.setExceptionDetails(ex.getMessage());
		apiError.getErrors().add("Somehow a null entity was given.");
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	protected ResponseEntity<ApiError> handleEntityNotfound(EmptyResultDataAccessException ex) {
		ApiError apiError = new ApiError();
		apiError.setExceptionDetails(ex.getMessage());
		apiError.getErrors().add("No Professional with the given ID was found.");
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<ApiError> handleNull(NullPointerException ex) {
		ApiError apiError = new ApiError();
		apiError.setExceptionDetails(ex.getMessage());
		apiError.getErrors().add("Null pointer exception.");
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiError> handleAllExceptions(Exception ex) {
		ApiError apiError = new ApiError();
		apiError.setExceptionDetails(ex.getMessage());
		apiError.getErrors().add(ex.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(APIException.class)
	public ResponseEntity<ApiError> processBindException(APIException ex) {
		ApiError apiError = new ApiError();
		BindingResult bindingResult = ex.getBindingResults();
		if (bindingResult != null && bindingResult.getAllErrors() != null)
			ex.getBindingResults().getAllErrors().forEach(error -> apiError.getErrors().add(error.getDefaultMessage()));

		apiError.setExceptionDetails(ex.getMessage());

		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
