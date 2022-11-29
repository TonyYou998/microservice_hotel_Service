package com.uit.microservice_hotel_service.controller;
import com.google.gson.Gson;
import com.uit.microservice_base_project.common.BaseConstant;
import com.uit.microservice_base_project.config.ResponseHandler;
import com.uit.microservice_hotel_service.dto.*;
import com.uit.microservice_hotel_service.common.HostConstant;
import com.uit.microservice_hotel_service.service.HostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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


   @PostMapping(HostConstant.BECOME_A_HOST)
   public Object becomeAHost(@RequestHeader(ROLE_HEADER) String role,@RequestHeader(UUID_HEADER) String uuid){
        if(!role.equals("User")&& !uuid.equals(""))
            return ResponseHandler.getResponse(HttpStatus.UNAUTHORIZED);
        return ResponseHandler.getResponse(hostService.becomeAHost(uuid),HttpStatus.OK);

   }

   @PostMapping(HostConstant.ADD_PROPERTY)
   public Object addProperty(@RequestHeader(ROLE_HEADER) String role, @RequestHeader(AUTHORIZATION_HEADER) String token, @RequestParam("model") String model, @RequestParam("file") MultipartFile file) throws IOException {

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
       dto.setImages(savedPath);
       CreatePropertyDto p=hostService.addProperty(dto,token);

       if(p==null)
           return ResponseHandler.getResponse("unable to create property",HttpStatus.BAD_REQUEST);
       return   ResponseHandler.getResponse(p,HttpStatus.OK);
   }
   @GetMapping(HostConstant.GET_PROPERTY_TYPE)
   public Object getPropertyType(@RequestHeader(ROLE_HEADER) String role){

        if(!role.equals("Host"))
            return  ResponseHandler.getResponse(HttpStatus.UNAUTHORIZED);
        return ResponseHandler.getResponse(hostService.getAllProperty(),HttpStatus.OK);
   }
   @GetMapping(HostConstant.GET_PROPERTY_BY_HOST_ID)
   public Object getPropertyByHostId(@RequestHeader(ROLE_HEADER) String role,@RequestHeader(AUTHORIZATION_HEADER) String token){
        if(!role.equals("Host"))
                return ResponseHandler.getResponse(HttpStatus.UNAUTHORIZED);
        return ResponseHandler.getResponse(hostService.getPropertyByHostId(token),HttpStatus.OK);

   }
   @GetMapping(HostConstant.GET_RECENT_PROPERTY)
   public Object getRecentProperty(){
        return hostService.getRecentProperty();

   }
}
