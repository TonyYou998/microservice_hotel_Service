package com.uit.microservice_hotel_service.controller;
import com.uit.microservice_hotel_service.dto.CreatePropertyDto;
import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.common.HotelConstant;
import com.uit.microservice_hotel_service.dto.DeleteRoomDto;
import com.uit.microservice_hotel_service.entities.Property;
import com.uit.microservice_hotel_service.service.PropertyService;
import com.uit.microservice_hotel_service.service.RoomService;
import com.uit.user_service.common.UserConstant;
import com.uit.user_service.dto.CreateUserDto;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(HotelConstant.BASE_URL)
@AllArgsConstructor
public class HotelController {

    private RoomService roomService;
    private PropertyService propertyService;
    @GetMapping(HotelConstant.become_a_host)
   public Object becomeHost(){

        return null;
   }

   @PostMapping(HotelConstant.create_a_room)
   public Object createRoom(@Valid @RequestBody CreateRoomDto dto, BindingResult result) {
       if(result.hasErrors()){
           return HotelConstant.ERROR;
       }
      return roomService.creataRoom(dto);
   }

   @PostMapping(HotelConstant.create_a_property)
   public Object createProperty(@Valid @RequestBody CreatePropertyDto dto, BindingResult result){
       if(result.hasErrors()){
           return HotelConstant.ERROR;
       }
        //System.out.println(dto.getAddress());
      // System.out.println(dto.getPropertyName());
        return propertyService.CreateProperty(dto);
      /// return propertyService.test();
   }

   @PostMapping(HotelConstant.delete_a_room)
   public Object deleteRoom(@Valid @RequestBody DeleteRoomDto id, BindingResult result){
       if(result.hasErrors()){
           return HotelConstant.ERROR;
       }
        System.out.println(id.getId());  // test
        return roomService.deleteRoom(id);
   }


   @GetMapping(HotelConstant.demo)
   public Object demo(){
        return "hotel Demo";
   }
}
