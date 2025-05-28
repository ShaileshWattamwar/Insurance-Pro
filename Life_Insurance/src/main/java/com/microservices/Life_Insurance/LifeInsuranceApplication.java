package com.microservices.Life_Insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LifeInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeInsuranceApplication.class, args);
	}

}
