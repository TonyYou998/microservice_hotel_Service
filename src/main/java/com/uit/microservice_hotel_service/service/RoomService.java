package com.uit.microservice_hotel_service.service;

import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.dto.RoomDto;

public interface RoomService {
    RoomDto creataRoom(CreateRoomDto dto);
}
