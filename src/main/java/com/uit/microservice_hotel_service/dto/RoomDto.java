package com.uit.microservice_hotel_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class RoomDto {

    private String roomType;
    private Double pricePerNight;
   // private UUID reserveByUserId;
    private boolean status=false;
    //private UUID propertyId;

}
