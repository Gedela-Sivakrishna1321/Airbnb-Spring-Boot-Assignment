package com.airbnb.Exceptions;

public class AuthException extends RuntimeException {
	
	public AuthException(String message) {
		super(message);
	}
}
