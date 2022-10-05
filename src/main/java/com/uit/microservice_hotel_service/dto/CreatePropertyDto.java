package com.uit.microservice_hotel_service.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class CreatePropertyDto {
    @NotEmpty
    private String propertyName;
    @NotEmpty
    private String address;
    @NotEmpty
    private String description;
    @NotEmpty
    private int propertyTypeId;
    @NotEmpty
    private String images;
    private UUID id;


}
