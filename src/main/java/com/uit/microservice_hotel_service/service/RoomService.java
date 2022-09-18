package com.uit.microservice_hotel_service.service;

import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.dto.DeleteRoomDto;
import com.uit.microservice_hotel_service.dto.RoomDto;

import java.util.UUID;

public interface RoomService {
    RoomDto creataRoom(CreateRoomDto dto);

    boolean deleteRoom(DeleteRoomDto dto);
}
