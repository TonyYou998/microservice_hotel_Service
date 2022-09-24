package com.uit.microservice_hotel_service.service;

import com.uit.microservice_hotel_service.controller.reponsitory.RoomRepository;
import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.dto.EdiRoomDto;
import com.uit.microservice_hotel_service.dto.RoomDto;
import com.uit.microservice_hotel_service.entities.Room;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        newRoom.setBedRoomCount(dto.getBedRoomCount());
        newRoom.setBathRoomCount(dto.getBathRoomCount());
        newRoom.setBedCount(dto.getBedCount());
        newRoom.setStatus(dto.isStatus());
        try{
            roomRepository.save(newRoom);
        } catch (Exception e){
            LOGGER.info(e.getCause().getMessage());
        }
        return mapper.map(newRoom, RoomDto.class);
    }

    @Override
    public RoomDto editRoom(EdiRoomDto dto) {
        Room editRoom = new Room();
        editRoom.setId(dto.getId());
        editRoom.setBedRoomCount(dto.getBedRoomCount());
        editRoom.setBathRoomCount(dto.getBathRoomCount());
        editRoom.setBedCount(dto.getBedCount());
        editRoom.setRoomType(dto.getRoomType());
        editRoom.setPricePerNight(dto.getPricePerNight());
        editRoom.setStatus(dto.isStatus());
        try{
            roomRepository.save(editRoom);
        } catch (Exception e){
            LOGGER.info(e.getCause().getMessage());
        }
        return mapper.map(editRoom, RoomDto.class);
    }


    @Override
    public boolean deleteRoom(UUID ID) {
        roomRepository.deleteById(ID);
        return true;
    }

    public  List<RoomDto> getAllRooms(){

        List<Room> rooms = new ArrayList<Room>();

        roomRepository.findAll().forEach(room -> rooms.add(room));

        List<RoomDto> roomDtos = rooms
                .stream()
                .map(user -> mapper.map(user, RoomDto.class))
                .collect(Collectors.toList());
        return roomDtos;
    }

    //public RoomDto
    public RoomDto getRoomById(UUID id) {
      return   mapper.map( roomRepository.findById(id).get(), RoomDto.class);
    }
}
