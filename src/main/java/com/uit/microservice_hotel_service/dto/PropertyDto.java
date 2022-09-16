package com.uit.microservice_hotel_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PropertyDto {

    private String propertyName;
    private int countryId;
    private int stateId;
    private int cityId;
    private String address;
    private String latitude;
    private String logitude;
    private String description;
    private int propertyTypeId;
    private Double rating;
    private String images;
    private int totalRoom;
    private int availableRoom;
    private int bedCount;
    private int bathRoomCount;
    private int guestCount;
    //    ?
    private LocalDateTime startDate;
    //    ?
    private LocalDateTime endDate;
    private  Long totalReview;
    private int propertyAmenityId;
    private UUID hostByUserId;
}
