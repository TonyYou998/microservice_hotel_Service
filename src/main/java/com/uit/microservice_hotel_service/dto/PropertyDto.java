package com.uit.microservice_hotel_service.dto;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public class PropertyDto {
    
    private String propertyName;
    private String address;
    private String description;
    private int propertyTypeId;
    private String images;
    private UUID id;


}
