package com.uit.microservice_hotel_service.entities;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import java.security.PrivateKey;
import java.util.UUID;
@Getter
@Setter
@Entity
public class FloorPlan extends BaseEntity {
    private int bedCount;
    private int bathRoomCount;
    private int guestCount;
    private UUID propertyId;
}
