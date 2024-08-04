package com.airbnb.Models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private String address;
	private BigDecimal pricePerNight;
	private int numberOfBedrooms;
	private int numberOfBathrooms;
	private boolean available;
	private boolean drinkAllowed;
	private boolean petAllowed;
	private int maxCheckoutTimeInNights;
	private BigDecimal extraCharges;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
//	@JsonBackReference
	private User owner;
	
}
