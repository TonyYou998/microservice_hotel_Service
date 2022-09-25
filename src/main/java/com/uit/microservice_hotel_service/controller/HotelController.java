package com.uit.microservice_hotel_service.controller;

import com.uit.microservice_base_project.common.BaseConstant;
import com.uit.microservice_hotel_service.common.HotelConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HotelConstant.BASE_URL)
public class HotelController {

   @GetMapping(HotelConstant.demo)
   public Object demo(){
        return "hotel Demo";
   }
}
