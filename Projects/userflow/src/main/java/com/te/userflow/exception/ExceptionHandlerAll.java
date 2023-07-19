package com.te.userflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerAll {

	@ExceptionHandler(AppointmentInvalidException.class)
	public ResponseEntity<?> exceptionHandler1(AppointmentInvalidException exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<?> exceptionhandler2(InvalidCredentialException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AppointmentNotFoundException.class)
	private ResponseEntity<?> exceptionHandler3(AppointmentNotFoundException e) {

		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	
}
