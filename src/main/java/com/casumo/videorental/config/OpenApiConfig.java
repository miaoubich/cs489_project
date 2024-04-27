package com.casumo.videorental.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Ali Bouzar", email = "ali@email.net", url = ""), title = "Video Rental Store", version = "1.0", description = "Open API documentation for a Secure Video Rental Store", license = @License(name = "licence name", url = "https://some-url.net"), termsOfService = "Terms of service"), servers = {
		@Server(description = "Local ENV", url = "http://localhost:8080"),
		@Server(description = "PROD ENV", url = "https://www.google.com") }, security = {
				@SecurityRequirement(name = "BEARER-AUTH") })
@SecurityScheme(name = "BEARER-AUTH", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER, scheme = "bearer", description = "JWT auth description")
public class OpenApiConfig {

}
