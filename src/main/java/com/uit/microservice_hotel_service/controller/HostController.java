package com.uit.microservice_hotel_service.controller;
import com.google.gson.Gson;
import com.uit.microservice_hotel_service.dto.*;
import com.uit.microservice_hotel_service.common.HostConstant;
import com.uit.microservice_hotel_service.service.HostService;
import common.BaseConstant;
import common.ResponseHandler;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final ModelMapper mapper;
    private final String uploadDir="/microservice_hotel_service/src/main/resources/static/upload/";
    private final String domainName="http://localhost:8082/";
    private static final Logger LOGGER= LoggerFactory.getLogger(HostController.class);


    @GetMapping(HostConstant.get_all_room)
    public List<RoomDto> getAllRoom(){
      try{  return hostService.getAllRooms();} catch (Exception e) {return null;}
    }
    @GetMapping(HostConstant.get_a_room)
    public Object getRoom(@PathVariable("id") UUID id) {
        try{
            Object dto= ResponseHandler.getResponse(hostService.getRoomById(id));
            return new ResponseEntity<>(dto,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        }
    }


    @PostMapping(HostConstant.create_a_room)
    public Object createRoom(@Valid @RequestBody CreateRoomDto dto, BindingResult result) {
       if(result.hasErrors()){
           return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);

       }
       Object response= ResponseHandler.getResponse(hostService.creataRoom(dto));
       return new ResponseEntity<>(response,HttpStatus.OK);

   }


    @PutMapping(HostConstant.edit_a_room)
    public Object editRoom(@Valid @RequestBody EdiRoomDto dto,BindingResult result, @PathVariable("id") UUID id) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        Object response= ResponseHandler.getResponse(hostService.editRoom(dto,id));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping(HostConstant.delete_a_room)
    public boolean deleteRoom(@PathVariable("id") UUID id) {
        try {
            hostService.deleteRoom(id);
            return true;
        }catch (Exception e) {  return false; }
    }


   @PostMapping(HostConstant.BECOME_A_HOST)
   public Object becomeAHost(@RequestHeader(ROLE_HEADER) String role,@RequestHeader(UUID_HEADER) String uuid){
        if(!role.equals("User")&& !uuid.equals(""))
            return ResponseHandler.getResponse(HttpStatus.UNAUTHORIZED);
        Object response= ResponseHandler.getResponse(hostService.becomeAHost(uuid));
        return new ResponseEntity<>(response,HttpStatus.OK);

   }

   @PostMapping(HostConstant.ADD_PROPERTY)
   public Object addProperty(@RequestHeader(ROLE_HEADER) String role, @RequestHeader(AUTHORIZATION_HEADER) String token, @RequestParam("model") String model,@RequestParam("file") MultipartFile file) throws IOException {

    Gson gson=new Gson();
    CreatePropertyDto dto= gson.fromJson(model,CreatePropertyDto.class);

       LOGGER.info(dto.toString());
        if(!role.equals("Host"))
            return ResponseHandler.getResponse(HttpStatus.UNAUTHORIZED);
        String fileName=file.getOriginalFilename();
        String userDir= Paths.get("").toAbsolutePath().toString();
        Path folderPath=Paths.get(userDir+uploadDir);
       if(!Files.exists(folderPath))
           Files.createDirectories(folderPath);
       Path path=Paths.get(userDir+uploadDir+fileName);
       Files.write(path, file.getBytes());
       final String savedPath=domainName+fileName;
       dto.setImg(savedPath);
       CreatePropertyDto p=hostService.addProperty(dto,token);

       if(p==null)
           return new ResponseEntity<>("unable to create property",HttpStatus.BAD_REQUEST);
       return   new ResponseEntity<>(p,HttpStatus.OK);
   }
   @GetMapping(HostConstant.GET_PROPERTY_TYPE)
   public Object getPropertyType(@RequestHeader(ROLE_HEADER) String role){

        if(!role.equals("Host"))
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Object dto= ResponseHandler.getResponse(hostService.getAllProperty());
        return new ResponseEntity<>(dto,HttpStatus.OK);
   }
   @GetMapping(HostConstant.GET_PROPERTY_BY_HOST_ID)
   public Object getPropertyByHostId(@RequestHeader(ROLE_HEADER) String role,@RequestHeader(AUTHORIZATION_HEADER) String token){
        if(!role.equals("Host"))
                return ResponseHandler.getResponse(HttpStatus.UNAUTHORIZED);
        Object dto= ResponseHandler.getResponse(hostService.getPropertyByHostId(token));
        return new ResponseEntity<>(dto,HttpStatus.OK);

   }
   @GetMapping(HostConstant.GET_RECENT_PROPERTY)
   public Object getRecentProperty(){
        return hostService.getRecentProperty();
   }
   @GetMapping(HostConstant.GET_PROPERTY_BY_ID)
    public GetPropertyDto getPropertyById(@PathVariable("propertyId") String propertyId){
        return hostService.findPropertyById(propertyId);

   }
//   @GetMapping(HostConstant.GET_HOST_ID_BY_PROPERTY_ID)
//   public UUID getHostIdByPropertyId(@PathVariable("propertyId") String propertyId){
//        return  hostService.findHostIdByPropertyId(UUID.fromString(propertyId));
//
//   }
}
