package com.javaweb.buildingproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class ModelMaperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
