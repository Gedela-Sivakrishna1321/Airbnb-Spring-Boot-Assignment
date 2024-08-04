package com.airbnb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.Config.JwtProvider;
import com.airbnb.Exceptions.AuthException;
import com.airbnb.Models.User;
import com.airbnb.Repository.UserRepository;
import com.airbnb.Requests.LoginRequest;
import com.airbnb.Response.AuthResponse;
import com.airbnb.Service.CustomUserImplementation;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomUserImplementation customUserImplementation;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> signUpHandler(@RequestBody User user) throws AuthException {
		
		String email = user.getEmail();
		String password = user.getPassword();
		
		User existedUser = userRepository.findByEmail(email);
		
		if(existedUser != null) { 
			throw new AuthException("user already registered with email : " + email);
		}
		
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setFullName(user.getFullName());
		newUser.setUsername(user.getUsername());
		newUser.setPhoneNumber(user.getPhoneNumber());
		newUser.setPassword(passwordEncoder.encode(password));
		
		User createdUser = userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, createdUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateJwtToken(authentication);
		
		AuthResponse response = new AuthResponse();
		response.setJwt(token);
		response.setMessage("Signup successfull ");
		System.out.println("Signup successfull ,, " + token);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> signInHandler(@RequestBody LoginRequest req ) throws AuthException {
		
		Authentication auth = authenticate(req.getEmail(), req.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String token = jwtProvider.generateJwtToken(auth);
		
		AuthResponse response = new AuthResponse();
		response.setJwt(token);
		response.setMessage("Login Successfull ");
		
		return ResponseEntity.ok(response);
	}
	
	
	public Authentication authenticate(String email, String password) throws AuthException {
		
		UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
		
		if(userDetails != null) {
			if(!passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new AuthException("Wrong Password ");
			}
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
	}
	

}
