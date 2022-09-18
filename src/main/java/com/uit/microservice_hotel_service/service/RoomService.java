package com.uit.microservice_hotel_service.service;

import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.dto.RoomDto;
import com.uit.microservice_hotel_service.entities.Room;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    RoomDto creataRoom(CreateRoomDto dto);

    boolean deleteRoom(UUID dto);

     List<Room> getAllRooms();

     Room getRoomById(UUID id);

}
