package com.uit.microservice_hotel_service.service;
import com.uit.microservice_hotel_service.entities.Property;
import dto.*;

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

    GetPropertyDto findPropertyById(String propertyId);

    Property findHostUserById(UUID uuid);

//    UUID findHostIdByPropertyId(UUID uuid);
}
