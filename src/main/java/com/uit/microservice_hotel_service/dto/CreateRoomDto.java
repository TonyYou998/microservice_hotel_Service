package com.uit.microservice_hotel_service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;


@Getter
@Setter
public class CreateRoomDto {

    @NotEmpty
    private String roomType;
    @NotEmpty
    private Double pricePerNight;
    @NotEmpty
    private UUID reserveByUserId;
    @NotEmpty
    private boolean status=false;
    @NotEmpty
    private UUID propertyId;
}
