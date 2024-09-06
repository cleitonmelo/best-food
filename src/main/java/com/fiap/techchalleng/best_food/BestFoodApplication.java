package com.fiap.techchalleng.best_food;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API Best Food Application",
				description = "Tech Challeng 3 - FIAP 2024 - Grupo 18",
				version = "1.0.0"
		)
)
public class BestFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestFoodApplication.class, args);
	}

}
