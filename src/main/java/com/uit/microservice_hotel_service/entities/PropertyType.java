package com.uit.microservice_hotel_service.entities;

import com.uit.user_service.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class PropertyType extends BaseEntity {
    private String name;
    private String image;

    @OneToMany(mappedBy = "id")
    private Set<Property> lstProperty;

    public PropertyType(String name) {
        this.name=name;
    }

    public PropertyType() {

    }
}
