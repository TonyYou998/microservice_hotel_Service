package com.uit.microservice_hotel_service.common.config;

import com.uit.microservice_base_project.config.BaseSecurityConfig;
import com.uit.user_service.common.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends BaseSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

//        http.antMatcher("/api/v1/host/**").authorizeRequests().anyRequest().authenticated();

    }
}
