package com.uit.microservice_hotel_service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;


@Getter
@Setter
public class CreateRoomDto {


    private String roomType;

    private Double pricePerNight;

    private UUID reserveByUserId;

    private boolean status;

    private UUID propertyId;
}
