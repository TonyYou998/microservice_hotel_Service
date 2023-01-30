package com.uit.microservice_hotel_service.service;
import com.uit.microservice_hotel_service.entities.Property;
import com.uit.microservice_hotel_service.entities.PropertyType;
import com.uit.microservice_hotel_service.repository.PropertyRepository;
import com.uit.microservice_hotel_service.repository.PropertyTypeRepository;
import com.uit.microservice_hotel_service.repository.RoomRepository;
import com.uit.microservice_hotel_service.entities.Room;
import dto.*;
import entities.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class HostServiceImpl implements HostService {

    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;
    private PropertyTypeRepository propertyTypeRepository;

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
        User u=restTemplate.getForObject("http://user/api/v1/user/changeRoleByUuid?uuid="+uuid,User.class);
        return mapper.map(u,UserDto.class);
    }

    @Override
    public CreatePropertyDto addProperty(CreatePropertyDto dto, String token) {
        Property p=new Property();
        try{
               UUID uid=  restTemplate.getForObject("http://user/api/v1/user/get-id?token="+token, UUID.class);
                PropertyType pT= propertyTypeRepository.findById(UUID.fromString(dto.getPropertyTypeId())).get();
                p.setPropertyName(dto.getPropertyName());
                p.setAddress(dto.getAddress());
                p.setDescription(dto.getDescription());
                p.setImages(dto.getImg());
                p.setLongitude(dto.getLongitude());
                p.setLatitude(dto.getLatitude());
                p.setPrivacy(dto.getPrivacy());
                p.setPropertyTypeId(pT);
                p.setHostUser(uid);
                p.setPrice(Double.parseDouble(dto.getPrice()));
                propertyRepository.save(p);
                return mapper.map(p,CreatePropertyDto.class);
        }
        catch (Exception e){
            LOGGER.info(e.getMessage());
            return null;
        }

    }

    @Override
    public List<GetPropertyTypeDto> getAllProperty() {
        List<PropertyType> lstPropertyTypes=propertyTypeRepository.findAll();
        List<GetPropertyTypeDto> rtnLstPropertyType=new LinkedList<>();
        for(PropertyType pT:lstPropertyTypes){
            GetPropertyTypeDto mapPropertyType=mapper.map(pT, GetPropertyTypeDto.class);
            rtnLstPropertyType.add(mapPropertyType);
        }
        return  rtnLstPropertyType;
    }

    @Override
    public List<GetPropertyDto> getPropertyByHostId(String token) {
        List<GetPropertyDto> lstDto=new LinkedList<>();
        GetPropertyDto dto;
        try {
            UUID uid=restTemplate.getForObject("http://user/api/v1/user/get-id?token="+token, UUID.class);
            List<Property> lstProperty= propertyRepository.findByHostUser(uid);
            for(Property p:lstProperty){
               dto=  mapper.map(p,GetPropertyDto.class);
               lstDto.add(dto);
            }
            return  lstDto;
        }
        catch(Exception e){
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    @Override
    public List<GetPropertyDto> getRecentProperty() {
        List<Property>lstProperty= propertyRepository.findAll();
        List<GetPropertyDto> lstDto=new LinkedList<>();
        for(Property p:lstProperty){
            GetPropertyDto dto= mapper.map(p,GetPropertyDto.class);
            lstDto.add(dto);
        }
        return  lstDto;
    }

    @Override
    public GetPropertyDto findPropertyById(String propertyId) {
       Property p=propertyRepository.findById(UUID.fromString(propertyId)).get();

         GetPropertyDto dto=mapper.map(p, GetPropertyDto.class);
            dto.setPropertyType(p.getPropertyTypeId().getName());
            dto.setHostUser(p.getHostUser().toString());
         return dto;
    }

    @Override
    public Property findHostUserById(UUID uuid) {
        return propertyRepository.findHostUserById(uuid);
    }

//    @Override
//    public UUID findHostIdByPropertyId(UUID uuid) {
//        return propertyRepository.findHostIdByPropertyId(uuid);
//    }


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
