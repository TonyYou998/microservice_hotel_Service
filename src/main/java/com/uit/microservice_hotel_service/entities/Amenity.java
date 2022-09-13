package com.uit.microservice_hotel_service.entities;

import com.uit.user_service.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Amenity extends BaseEntity {
    private String name;
    private String image;

}
