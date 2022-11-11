package com.uit.microservice_hotel_service.service;

import com.uit.microservice_hotel_service.dto.CreatePropertyDto;
import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.dto.EdiRoomDto;

import com.uit.microservice_hotel_service.dto.RoomDto;
import com.uit.user_service.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface HostService {
    RoomDto creataRoom(CreateRoomDto dto);
    boolean deleteRoom(UUID dto);
     List<RoomDto> getAllRooms();
     RoomDto getRoomById(UUID id);
     RoomDto editRoom(EdiRoomDto dto , UUID id);

    UserDto becomeAHost(String uuid);

    CreatePropertyDto addProperty(CreatePropertyDto dto, String token);
}
