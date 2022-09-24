package com.uit.microservice_hotel_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
public class RoomDto {

    private UUID id;
    private int bedRoomCount;
    private int bathRoomCount;
    private int bedCount;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String roomType;
    private Double pricePerNight;
    private UUID reserveByUserId;
    private boolean status=false;
    private UUID propertyId;

}
