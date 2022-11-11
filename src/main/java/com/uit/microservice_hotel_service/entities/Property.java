package com.uit.microservice_hotel_service.entities;

import com.uit.user_service.entities.BaseEntity;
import com.uit.user_service.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="property_type_id")
    private PropertyType propertyTypeId;
    private String privacy;
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
    @Type(type = "uuid-char")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID hostByUserId;



}
