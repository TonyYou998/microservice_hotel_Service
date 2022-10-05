package com.uit.microservice_hotel_service.service;

import com.uit.microservice_hotel_service.dto.PropertyDto;
import com.uit.microservice_hotel_service.entities.Property;
import com.uit.microservice_hotel_service.repository.PropertyRepository;
import com.uit.microservice_hotel_service.repository.RoomRepository;
import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.dto.EdiRoomDto;
import com.uit.microservice_hotel_service.dto.RoomDto;
import com.uit.microservice_hotel_service.entities.Room;
import com.uit.user_service.dto.UserDto;
import com.uit.user_service.entities.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class HostServiceImpl implements HostService {

    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;

    private static final Logger LOGGER= LoggerFactory.getLogger(HostService.class);
    private final ModelMapper mapper;
    private RestTemplate restTemplate;

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
    public RoomDto editRoom(EdiRoomDto dto, UUID id) {
        Room editRoom = roomRepository.findById(id).get();
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
    public UserDto becomeAHost(String uuid) {
        User u=restTemplate.getForObject("http://user-service/api/v1/user/changeRoleByUuid?uuid="+uuid,User.class);
        return mapper.map(u,UserDto.class);
    }

    @Override
    public PropertyDto addProperty(PropertyDto dto) {
        Property p=new Property();
        p.setPropertyName(dto.getPropertyName());
        p.setAddress(dto.getAddress());
        p.setDescription(dto.getDescription());
        p.setImages(dto.getImages());
        propertyRepository.save(p);

        return mapper.map(p,PropertyDto.class);
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
