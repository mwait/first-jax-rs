package com.wait.jaxrs.exceptions;

public class SomeBusinessException extends RuntimeException {

	public SomeBusinessException(String message){
		super(message);
	}
}
