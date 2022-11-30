package com.uit.microservice_hotel_service.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreatePropertyDto {
//    @NotEmpty
    private String propertyName;
    @NotEmpty
    private String address;
//    @NotEmpty
    private String description;
    @NotNull
    private String propertyTypeId;
    @NotNull
    private String price;
    @NotEmpty
    private String latitude;
    @NotEmpty
    private String longitude;
//    @NotEmpty
    private String img;
    @NotEmpty
    private String privacy;
    private String country;
    private String city;
    private UUID hostByUserId;



}
