package com.uit.microservice_hotel_service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EdiRoomDto {


    private UUID id;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String roomType;
    private Double pricePerNight;
    private UUID reserveByUserId;
    private boolean status;
    private UUID propertyId;


}
