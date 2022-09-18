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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        newRoom.setStatus(dto.isStatus());
        try{
            roomRepository.save(newRoom);
        } catch (Exception e){
            LOGGER.info(e.getCause().getMessage());
        }
        return mapper.map(newRoom, RoomDto.class);
    }


    @Override
    public boolean deleteRoom(UUID ID) {
        roomRepository.deleteById(ID);
        return true;
    }

    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<Room>();
        roomRepository.findAll().forEach(room -> rooms.add(room));
        return rooms;
    }

    //public RoomDto

    public Room getRoomById(UUID id) {
        return roomRepository.findById(id).get();
    }


}
