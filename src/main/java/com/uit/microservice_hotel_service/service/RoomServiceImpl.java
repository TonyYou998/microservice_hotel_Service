package com.uit.microservice_hotel_service.service;

import com.uit.microservice_hotel_service.controller.reponsitory.RoomRepository;
import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.dto.RoomDto;
import com.uit.microservice_hotel_service.entities.Room;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

  private RoomRepository roomRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(RoomService.class);
    private final ModelMapper mapper;


    @Override
    public RoomDto creataRoom(CreateRoomDto dto) {
        Room newRoom = new Room();
        newRoom.setRoomType(dto.getRoomType());
        newRoom.setPricePerNight(dto.getPricePerNight());
        newRoom.setStatus(false);
        try{
            roomRepository.save(newRoom);
        } catch (Exception e){
            LOGGER.info(e.getCause().getMessage());
        }

        return mapper.map(newRoom, RoomDto.class);
    }
}
