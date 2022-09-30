package com.uit.microservice_hotel_service.controller;
import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.common.HotelConstant;
import com.uit.microservice_hotel_service.dto.EdiRoomDto;
import com.uit.microservice_hotel_service.dto.RoomDto;
import com.uit.microservice_hotel_service.entities.Room;
import com.uit.microservice_hotel_service.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(HotelConstant.BASE_URL)
@AllArgsConstructor
public class HotelController {

    private RoomService roomService;
    @GetMapping(HotelConstant.become_a_host)
   public Object becomeHost(){

        return null;
   }

    @GetMapping(HotelConstant.get_all_room)
    public List<RoomDto> getAllRoom(){
      try{  return roomService.getAllRooms();} catch (Exception e) {return null;}
    }
    @GetMapping(HotelConstant.get_a_room)
    public RoomDto getRoom(@PathVariable("id") UUID id) {
        try{return roomService.getRoomById(id);} catch (Exception e) {return null;}
    }

    @CrossOrigin
    @PostMapping(HotelConstant.create_a_room)
    public Object createRoom(@Valid @RequestBody CreateRoomDto dto, BindingResult result) {
       if(result.hasErrors()){
           return HotelConstant.ERROR;
       }
      return roomService.creataRoom(dto);
   }

    @CrossOrigin
    @PutMapping(HotelConstant.edit_a_room)
    public Object editRoom(@Valid @RequestBody EdiRoomDto dto,BindingResult result, @PathVariable("id") UUID id) {
        if(result.hasErrors()){
        }
        return roomService.editRoom(dto,id);
    }

    @DeleteMapping(HotelConstant.delete_a_room)
    public boolean deleteRoom(@PathVariable("id") UUID id) {
        try {
            roomService.deleteRoom(id);
            return true;
        }catch (Exception e) {  return false; }
    }


   @GetMapping(HotelConstant.demo)
   public Object demo(){
        return "hotel Demo";
   }
}
