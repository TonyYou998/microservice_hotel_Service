package com.uit.microservice_hotel_service.controller.reponsitory;

import com.uit.microservice_hotel_service.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

}