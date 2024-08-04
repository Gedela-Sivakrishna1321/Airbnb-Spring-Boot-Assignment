package com.airbnb.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorize ->
					authorize
					.requestMatchers("/api/auth/signup", "/api/auth/login").permitAll()
					.anyRequest().authenticated())
			.addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
			.csrf(csrf -> csrf.disable())
			.exceptionHandling(exceptionHandler -> exceptionHandler.authenticationEntryPoint(customAuthenticationEntryPoint))
			.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
