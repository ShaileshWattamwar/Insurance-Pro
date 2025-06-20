package com.claimservice.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServicesRegistryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesRegistryManagementApplication.class, args);
		
		System.out.println("\n\tServicesRegistryManagementApplication.main()\n");
		
	}

}
