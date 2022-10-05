package com.uit.microservice_hotel_service.controller;
import com.uit.microservice_base_project.common.BaseConstant;
import com.uit.microservice_hotel_service.dto.CreatePropertyDto;
import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.common.HostConstant;
import com.uit.microservice_hotel_service.dto.EdiRoomDto;
import com.uit.microservice_hotel_service.dto.RoomDto;
import com.uit.microservice_hotel_service.service.HostService;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(BaseConstant.BASE_URL+ HostConstant.SERVICE_NAME)
@AllArgsConstructor
public class HostController {
    private final String ROLE_HEADER="Role";
    private final String UUID_HEADER="UUID";
    private HostService hostService;


    @GetMapping(HostConstant.get_all_room)
    public List<RoomDto> getAllRoom(){
      try{  return hostService.getAllRooms();} catch (Exception e) {return null;}
    }
    @GetMapping(HostConstant.get_a_room)
    public RoomDto getRoom(@PathVariable("id") UUID id) {
        try{return hostService.getRoomById(id);} catch (Exception e) {return null;}
    }

    @CrossOrigin
    @PostMapping(HostConstant.create_a_room)
    public Object createRoom(@Valid @RequestBody CreateRoomDto dto, BindingResult result) {
       if(result.hasErrors()){
           return HostConstant.ERROR;
       }
      return hostService.creataRoom(dto);
   }

    @CrossOrigin
    @PutMapping(HostConstant.edit_a_room)
    public Object editRoom(@Valid @RequestBody EdiRoomDto dto,BindingResult result, @PathVariable("id") UUID id) {
        if(result.hasErrors()){
        }
        return hostService.editRoom(dto,id);
    }

    @DeleteMapping(HostConstant.delete_a_room)
    public boolean deleteRoom(@PathVariable("id") UUID id) {
        try {
            hostService.deleteRoom(id);
            return true;
        }catch (Exception e) {  return false; }
    }


   @GetMapping(HostConstant.demo)
   public Object demo(){
        return "hotel Demo";
   }

   @PostMapping(HostConstant.BECOME_A_HOST)
   public Object becomeAHost(@RequestHeader(ROLE_HEADER) String role,@RequestHeader(UUID_HEADER) String uuid){
        if(!role.equals("User")&& !uuid.equals(""))
            return HostConstant.ERROR;
        return hostService.becomeAHost(uuid);

   }

   @PostMapping(HostConstant.ADD_PROPERTY)
   public Object addProperty(@RequestHeader(ROLE_HEADER) String role,@Valid @RequestBody CreatePropertyDto dto,BindingResult result){

            if(!role.equals("Host"))
                return  HostConstant.ERROR;
       if(result.hasErrors())
           return HostConstant.ERROR;
            return   hostService.addProperty(dto);
   }
}
