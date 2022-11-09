package com.uit.microservice_hotel_service.repository;

import com.uit.microservice_hotel_service.entities.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyTypeRepository extends JpaRepository<PropertyType, UUID> {

}
