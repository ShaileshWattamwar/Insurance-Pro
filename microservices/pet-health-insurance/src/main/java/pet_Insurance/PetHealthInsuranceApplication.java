package pet_Insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer

public class PetHealthInsuranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetHealthInsuranceApplication.class, args);
	}

}
