package com.airbnb.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.airbnb.Response.Response;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(PropertyException.class)
	public ResponseEntity<Response> handlePropertyException(PropertyException ex) {
		Response response = new Response();
		response.setMessage(ex.getMessage());
		return ResponseEntity.ok(response);
	}
	
	@ExceptionHandler(AuthException.class)
	public ResponseEntity<Response> handleAutheticationException(AuthException ex) {
		Response response = new Response();
		response.setMessage(ex.getMessage());
		return ResponseEntity.ok(response);
	}
	
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Response> handleBadcredentialsException(BadCredentialsException ex) {
		Response response = new Response();
		response.setMessage(ex.getMessage());
		return ResponseEntity.ok(response);
	}
}
