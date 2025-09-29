package com.vber.abank.account_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI accountServiceOpenAPI() {
    return new OpenAPI()
        .info(new Info()
        .title("Account Service API")
        .version("1.0")
        .description("API documentation for the Account Service"))
        .externalDocs(new ExternalDocumentation()
            .description("description")
            .url("http://description.html"));
  }
}
