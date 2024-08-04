package com.airbnb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.Exceptions.PropertyException;
import com.airbnb.Models.Property;
import com.airbnb.Models.User;
import com.airbnb.Response.Response;
import com.airbnb.Service.PropertyService;
import com.airbnb.Service.UserService;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PropertyService propertyService;
	
	@PostMapping()
	public ResponseEntity<Property> createNewPropertyHandler(
			@RequestHeader("Authorization") String jwt,
			@RequestBody Property reqproperty
			) {
		User user = userService.getUserFromJwtToken(jwt);
//		System.out.println("Create Property -> " + reqproperty);
		Property createProperty = propertyService.createProperty(reqproperty, user);
		
		return ResponseEntity.ok(createProperty);
	}
	
	@GetMapping()
	public ResponseEntity<List<Property>> getAllPopertiesHandler() {
		List<Property> allProperties = propertyService.getAllProperties();
		return ResponseEntity.ok(allProperties);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Property> getPropertyByIdHandler(@PathVariable Long id) throws PropertyException {
		Property property = propertyService.getPropertyById(id);
		return ResponseEntity.ok(property);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Property> updatePropertyHandler(
			@PathVariable Long id,
			@RequestBody Property reqProperty
			) {
		reqProperty.setId(id);
		
		Property updatedProperty = propertyService.updateProperty(reqProperty);
		return ResponseEntity.ok(updatedProperty);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deletePropertyHandler(@PathVariable Long id) {
		
		propertyService.deleteProperty(id);
		
		Response response = new Response();
		response.setMessage("Property deleted successfully ");
		
		return ResponseEntity.ok(response);
	}
	
	
	

}
