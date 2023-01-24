package com.uit.microservice_hotel_service.repository;

import com.uit.microservice_hotel_service.entities.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, UUID> {

}
