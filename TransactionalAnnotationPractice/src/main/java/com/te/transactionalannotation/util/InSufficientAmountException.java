package com.te.transactionalannotation.util;

public class InSufficientAmountException extends RuntimeException {

	InSufficientAmountException(String msg){
		super(msg);
	}
}
