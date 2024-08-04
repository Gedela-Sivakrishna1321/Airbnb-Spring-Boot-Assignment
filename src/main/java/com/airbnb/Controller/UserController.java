package com.airbnb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.Models.Property;
import com.airbnb.Service.PropertyService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private PropertyService propertyService;
	
	@GetMapping("/{userId}/properties")
	public ResponseEntity<List<Property>> getAllUserProperties(@PathVariable Long userId) {
		
		List<Property> allUserProperties = propertyService.getAllUserProperties(userId);
		
		return ResponseEntity.ok(allUserProperties);
	}

}
