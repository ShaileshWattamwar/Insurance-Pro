package com.claim.preauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PreAuthClaimServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreAuthClaimServicesApplication.class, args);
		
		System.out.println("\n\tPreAuthClaimServicesApplication.main()\n");
	
	}

}
