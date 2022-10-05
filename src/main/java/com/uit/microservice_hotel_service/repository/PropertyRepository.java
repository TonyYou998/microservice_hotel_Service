package com.uit.microservice_hotel_service.repository;

import com.uit.microservice_hotel_service.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {
}
