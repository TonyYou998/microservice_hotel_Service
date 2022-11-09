package com.uit.microservice_hotel_service.entities;

import com.uit.user_service.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Entity
public class Property extends BaseEntity {
    private String propertyName;
    private int countryId;
    private int stateId;
    private int cityId;
    private String address;
    private String latitude;
    private String longitude;
    private String description;
    private int propertyTypeId;
    private Double rating;
    private String images;
    private int totalRoom;
    private int availableRoom;
//    floor-plan
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
