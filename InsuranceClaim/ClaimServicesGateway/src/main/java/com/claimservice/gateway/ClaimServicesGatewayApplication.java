package com.claimservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClaimServicesGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimServicesGatewayApplication.class, args);
		
		System.out.println("\n\tClaimServicesGatewayApplication.main()\n");
	}

}
