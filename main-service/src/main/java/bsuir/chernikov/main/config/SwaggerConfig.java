package bsuir.chernikov.main.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DeliveryGO REST API")
                        .description("A software tool for organizing and automating the " +
                                "parcel delivery process with the participation of users and couriers")
                        .version("1.0.0"));
    }
}