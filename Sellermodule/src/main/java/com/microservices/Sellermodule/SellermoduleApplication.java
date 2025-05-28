package com.microservices.Sellermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SellermoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellermoduleApplication.class, args);
	}

}
