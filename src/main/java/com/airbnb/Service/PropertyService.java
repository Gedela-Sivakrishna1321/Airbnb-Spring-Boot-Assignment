package com.airbnb.Service;

import java.util.List;

import com.airbnb.Exceptions.PropertyException;
import com.airbnb.Models.Property;
import com.airbnb.Models.User;

public interface PropertyService {
	
	public Property createProperty(Property property, User user);
	
	public List<Property> getAllProperties();
	
	public Property getPropertyById(Long id) throws PropertyException;
	
	public Property updateProperty(Property updatedProperty);
	
	public List<Property> getAllUserProperties(Long userId);
	
	public void deleteProperty(Long id);
}
