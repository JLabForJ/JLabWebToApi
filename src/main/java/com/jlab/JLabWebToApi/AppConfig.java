package com.jlab.JLabWebToApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.jlab.JLabWebToApi.service.CustomerService;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public CustomerService customerService() {
        return new CustomerService(restTemplate());
    }
    
}



