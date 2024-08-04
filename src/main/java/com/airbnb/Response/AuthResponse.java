package com.airbnb.Response;

import lombok.Data;

@Data
public class AuthResponse {
	
	String jwt;
	String message;
}
