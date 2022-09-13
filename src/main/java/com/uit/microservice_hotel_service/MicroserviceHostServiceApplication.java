package com.uit.microservice_hotel_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MicroserviceHostServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceHostServiceApplication.class, args);
    }

}
