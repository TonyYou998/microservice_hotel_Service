package com.uit.microservice_hotel_service.entities;

import com.uit.user_service.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Room extends BaseEntity {
    private int bedRoomCount;
    private int bathRoomCount;
    private int bedCount;
    private String roomType;
    private Double pricePerNight;
    private UUID reserveByUserId;
    private boolean status=false;
    private UUID propertyId;
}
