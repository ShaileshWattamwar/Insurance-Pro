package com.microservices.Life_Insurance.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Model {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
