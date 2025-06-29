package com.avinash.vault;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Vault API", version = "1.0", description = "API for managing credentials in a vault"))
public class VaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultApplication.class, args);
	}

}
