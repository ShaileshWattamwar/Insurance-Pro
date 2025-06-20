package com.claim.payment.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
	
	@Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
