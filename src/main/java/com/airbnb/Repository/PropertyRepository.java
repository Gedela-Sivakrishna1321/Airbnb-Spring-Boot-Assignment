package com.airbnb.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airbnb.Models.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	@Query("SELECT p FROM Property p WHERE p.owner.id = :userId")
	public List<Property> getAllUserProperties(@Param("userId") Long userId);
	
}
