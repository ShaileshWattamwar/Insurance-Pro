package pet_Insurance.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocumentation {

    // springdoc openApi

    @Bean
    public OpenAPI petInsuranceOpenAPI() {
        return new OpenAPI()
          .info(new Info()
          .title("Pet Health Insurance API")
          .version("1.0")
          .description("API for managing pet insurance policies"));
    }
}
