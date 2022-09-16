package com.uit.microservice_hotel_service.service;

import com.uit.microservice_hotel_service.dto.CreatePropertyDto;
import com.uit.microservice_hotel_service.dto.PropertyDto;

public interface PropertyService {
    PropertyDto CreateProperty(CreatePropertyDto dto);
}
