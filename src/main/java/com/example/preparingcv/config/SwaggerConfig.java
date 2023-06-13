package com.example.preparingcv.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("NOTIFICATION API")
                        .version("1.0")
                        .description("NOTIFICATION")
                        .license(new License().name("NOTIFICATION API LICENCE")));
    }

}
