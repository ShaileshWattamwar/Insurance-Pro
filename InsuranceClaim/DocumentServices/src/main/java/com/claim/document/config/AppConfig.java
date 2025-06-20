package com.claim.document.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	ModelMapper ModelMapper() {
		return new ModelMapper();
	}
	
}
