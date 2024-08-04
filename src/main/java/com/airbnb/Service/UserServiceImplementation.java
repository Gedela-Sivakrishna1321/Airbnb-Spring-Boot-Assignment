package com.airbnb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.Config.JwtProvider;
import com.airbnb.Models.User;
import com.airbnb.Repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User getUserById(Long userId) throws Exception {
		return userRepository.findById(userId).orElseThrow(() -> new Exception("User Not found with id " + userId));
		
	}

	@Override
	public User getUserFromJwtToken(String jwt) {
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		
		return user;
	}

}
