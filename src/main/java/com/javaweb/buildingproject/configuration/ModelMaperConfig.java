package com.javaweb.buildingproject.configuration;

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
