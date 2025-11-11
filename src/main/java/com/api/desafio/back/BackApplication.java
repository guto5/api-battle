package com.api.desafio.back;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@OpenAPIDefinition(info = @Info(
				title = "API de Posts da Rede Social",
				version = "1.0",
				description = "API para gerenciar posts, comentários e curtidas."
))
@SpringBootApplication
@SecurityScheme(
				name = "bearerAuth",
				type = SecuritySchemeType.HTTP,
				scheme = "bearer",
				bearerFormat = "JWT"
)
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

}
