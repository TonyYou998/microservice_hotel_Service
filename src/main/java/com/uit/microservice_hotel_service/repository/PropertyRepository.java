package com.uit.microservice_hotel_service.repository;

import com.uit.microservice_hotel_service.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {


    List<Property> findByHostUser(UUID uid);

//    Property findHostUserById(UUID uuid);

    Property findHostUserById(UUID uuid);

//    UUID findHostIdByPropertyId(UUID uuid);
}
