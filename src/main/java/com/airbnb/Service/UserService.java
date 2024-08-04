package com.airbnb.Service;


import com.airbnb.Models.User;

public interface UserService {
	
	public User getUserById(Long userId) throws Exception;
	
	public User getUserFromJwtToken(String jwt);
	
}
