package com.uit.microservice_hotel_service.controller;
import com.uit.microservice_base_project.common.BaseConstant;
import com.uit.microservice_base_project.config.ResponseHandler;
import com.uit.microservice_hotel_service.dto.CreatePropertyDto;
import com.uit.microservice_hotel_service.dto.CreateRoomDto;
import com.uit.microservice_hotel_service.common.HostConstant;
import com.uit.microservice_hotel_service.dto.EdiRoomDto;
import com.uit.microservice_hotel_service.dto.RoomDto;
import com.uit.microservice_hotel_service.service.HostService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(BaseConstant.BASE_URL+ HostConstant.SERVICE_NAME)
@AllArgsConstructor
public class HostController {
    private final String ROLE_HEADER="Role";
    private final String AUTHORIZATION_HEADER="Authorization";
    private final String UUID_HEADER="UUID";
    private HostService hostService;
    private static final Logger LOGGER= LoggerFactory.getLogger(HostController.class);


    @GetMapping(HostConstant.get_all_room)
    public List<RoomDto> getAllRoom(){
      try{  return hostService.getAllRooms();} catch (Exception e) {return null;}
    }
    @GetMapping(HostConstant.get_a_room)
    public Object getRoom(@PathVariable("id") UUID id) {
        try{
            return ResponseHandler.getResponse(hostService.getRoomById(id),HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return ResponseHandler.getResponse(HttpStatus.REQUEST_TIMEOUT);
        }
    }


    @PostMapping(HostConstant.create_a_room)
    public Object createRoom(@Valid @RequestBody CreateRoomDto dto, BindingResult result) {
       if(result.hasErrors()){
           return ResponseHandler.getResponse(result,HttpStatus.BAD_REQUEST);
       }
      return ResponseHandler.getResponse(hostService.creataRoom(dto),HttpStatus.OK);
   }


    @PutMapping(HostConstant.edit_a_room)
    public Object editRoom(@Valid @RequestBody EdiRoomDto dto,BindingResult result, @PathVariable("id") UUID id) {
        if(result.hasErrors()){
            return ResponseHandler.getResponse(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.getResponse(hostService.editRoom(dto,id),HttpStatus.OK);
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
            return ResponseHandler.getResponse(HttpStatus.UNAUTHORIZED);
        return ResponseHandler.getResponse(hostService.becomeAHost(uuid),HttpStatus.OK);

   }

   @PostMapping(HostConstant.ADD_PROPERTY)
   public Object addProperty(@RequestHeader(ROLE_HEADER) String role,@RequestHeader(AUTHORIZATION_HEADER) String token, @Valid @RequestBody CreatePropertyDto dto, BindingResult result){
    LOGGER.info(dto.toString());
        if(!role.equals("Host"))
            return ResponseHandler.getResponse(HttpStatus.UNAUTHORIZED);
       if(result.hasErrors())
           return ResponseHandler.getResponse(result,HttpStatus.BAD_REQUEST);
       CreatePropertyDto p=hostService.addProperty(dto,token);
       if(p==null)
           return ResponseHandler.getResponse("unable to create property",HttpStatus.BAD_REQUEST);
       return   ResponseHandler.getResponse(p,HttpStatus.OK);
   }
}
