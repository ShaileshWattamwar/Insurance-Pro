package com.insurance.claim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class HealthInsuranceClaimManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthInsuranceClaimManagementApplication.class, args);
		
		System.out.println("\n\tHealthInsuranceClaimManagementApplication.main()\n");
	}

}
