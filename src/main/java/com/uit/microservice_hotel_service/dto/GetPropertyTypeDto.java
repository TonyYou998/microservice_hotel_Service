package com.uit.microservice_hotel_service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GetPropertyTypeDto {
    private UUID id;
    private String name;
    private  String image;
}
