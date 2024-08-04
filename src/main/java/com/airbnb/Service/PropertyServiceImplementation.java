package com.airbnb.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.Exceptions.PropertyException;
import com.airbnb.Models.Property;
import com.airbnb.Models.User;
import com.airbnb.Repository.PropertyRepository;

@Service
public class PropertyServiceImplementation implements PropertyService {
	
	@Autowired
	private PropertyRepository propertyRepository;

	@Override
	public Property createProperty(Property property, User user) {
		 
		// Check before including
//		System.out.println("IsAvailable before including -> " + property.isAvailable());
		Property newProperty = new Property();
		newProperty.setName(property.getName());
		newProperty.setDescription(property.getDescription());
		newProperty.setAddress(property.getAddress());
		newProperty.setPricePerNight(property.getPricePerNight());
		newProperty.setNumberOfBedrooms(property.getNumberOfBedrooms());
		newProperty.setNumberOfBathrooms(property.getNumberOfBathrooms());
		newProperty.setAvailable(property.isAvailable());
		newProperty.setDrinkAllowed(property.isDrinkAllowed());
		newProperty.setPetAllowed(property.isPetAllowed());
		newProperty.setMaxCheckoutTimeInNights(property.getMaxCheckoutTimeInNights());
		newProperty.setExtraCharges(property.getExtraCharges());
		newProperty.setOwner(user);
		
		// Check availability after including
//		System.out.println("After including isAvailable -> " + newProperty.isAvailable());
		
		return propertyRepository.save(newProperty);
	}

	@Override
	public List<Property> getAllProperties() {
		
		return propertyRepository.findAll();
	}

	@Override
	public Property getPropertyById(Long id) throws PropertyException {
		
		return propertyRepository.findById(id)
		        .orElseThrow(() -> new PropertyException("Property not found with id " + id));

	}

	@Override
	public Property updateProperty(Property updatedProperty) {
		
		Optional<Property> optional = propertyRepository.findById(updatedProperty.getId());
		Property currProperty = optional.get();
		
		String name = updatedProperty.getName();
		String description = updatedProperty.getDescription();
		String address = updatedProperty.getAddress();
		BigDecimal pricePerNight = updatedProperty.getPricePerNight();
		int numberOfBedrooms = updatedProperty.getNumberOfBedrooms();
		int numberOfBathrooms = updatedProperty.getNumberOfBathrooms();
		boolean available = updatedProperty.isAvailable();
		boolean drinkAllowed = updatedProperty.isDrinkAllowed();
		boolean petAllowed = updatedProperty.isPetAllowed();
		int maxCheckoutTimeInNights = updatedProperty.getMaxCheckoutTimeInNights();
		BigDecimal extraCharges = updatedProperty.getExtraCharges();

		
		if(name != null) {
			currProperty.setName(name);
		}
		
		if(description != null) {
			currProperty.setDescription(description);
		}
		
		if(address != null) {
			currProperty.setAddress(address);
		}
		
		if(pricePerNight != null) {
			currProperty.setPricePerNight(pricePerNight);
		}
		
		if(numberOfBedrooms != currProperty.getNumberOfBedrooms()) {
			currProperty.setNumberOfBedrooms(numberOfBedrooms);
		}
		
		if(numberOfBathrooms != currProperty.getNumberOfBathrooms()) {
			currProperty.setNumberOfBathrooms(numberOfBathrooms);
		}
		
		if(available != currProperty.isAvailable()) {
			currProperty.setAvailable(available);
		}
		
		if(drinkAllowed != currProperty.isDrinkAllowed()) {
			currProperty.setDrinkAllowed(drinkAllowed);
		}
		
		if(petAllowed != currProperty.isPetAllowed()) {
			currProperty.setPetAllowed(petAllowed);
		}
		
		if(maxCheckoutTimeInNights != currProperty.getMaxCheckoutTimeInNights()) {
			currProperty.setMaxCheckoutTimeInNights(maxCheckoutTimeInNights);
		}
		
		if(extraCharges != null) {
			currProperty.setExtraCharges(extraCharges);
		}
		
		return propertyRepository.save(currProperty);
	}

	@Override
	public List<Property> getAllUserProperties(Long userId) {
		
		return propertyRepository.getAllUserProperties(userId);
	}

	@Override
	public void deleteProperty(Long id) {
		
		propertyRepository.deleteById(id);
		
	}

}
