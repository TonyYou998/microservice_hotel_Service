package com.uit.microservice_hotel_service.seeder;

import com.uit.microservice_hotel_service.entities.PropertyType;
import com.uit.microservice_hotel_service.repository.PropertyTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@AllArgsConstructor
@Component
public class PropertyCategorySeeder {
    private JdbcTemplate jdbcTemplate;
    private PropertyTypeRepository propertyTypeRepository;
    @EventListener
    public void PropertyTypeSeeder(ContextRefreshedEvent contextRefreshedEvent){
        seedPropertyCategoty();

    }
    private void seedPropertyCategoty(){
        String query="SELECT * FROM property_type";
        List<PropertyType> lstType =jdbcTemplate.query(query,(ResultSet, rowNum)->null);
        if(lstType.size()==0){
                PropertyType pT1=new PropertyType("House");
                PropertyType pT2=new PropertyType("HomeStay");
                PropertyType pT3=new PropertyType("Apartment");
                PropertyType pT4=new PropertyType("Villa");
                propertyTypeRepository.save(pT1);
                propertyTypeRepository.save(pT2);
                propertyTypeRepository.save(pT3);
                propertyTypeRepository.save(pT4);
        }
    }
}
