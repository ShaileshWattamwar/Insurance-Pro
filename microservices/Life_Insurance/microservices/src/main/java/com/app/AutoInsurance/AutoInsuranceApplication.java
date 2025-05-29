package com.app.AutoInsurance;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AutoInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoInsuranceApplication.class, args);
	}

}
