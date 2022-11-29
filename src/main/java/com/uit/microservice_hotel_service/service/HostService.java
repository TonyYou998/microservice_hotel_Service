package com.uit.microservice_hotel_service.service;

import com.uit.microservice_hotel_service.dto.*;

import com.uit.microservice_hotel_service.entities.Property;
import com.uit.microservice_hotel_service.entities.PropertyType;
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

    List<GetPropertyTypeDto> getAllProperty();

    List<GetPropertyDto> getPropertyByHostId(String token);


    List<GetPropertyDto> getRecentProperty();
}
