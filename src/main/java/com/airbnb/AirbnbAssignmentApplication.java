package com.airbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.airbnb.Models.Property;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AirbnbAssignmentApplication {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		SpringApplication.run(AirbnbAssignmentApplication.class, args);
		System.out.println("App running successfully at port : 8080");
	
	}

}
