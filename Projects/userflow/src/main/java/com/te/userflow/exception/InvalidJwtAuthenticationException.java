package com.te.userflow.exception;

public class InvalidJwtAuthenticationException extends RuntimeException {

	public InvalidJwtAuthenticationException(String msg){
		super(msg);
	}
}
