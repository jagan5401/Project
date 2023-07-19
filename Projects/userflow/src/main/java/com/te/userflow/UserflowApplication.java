package com.te.userflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "UserFlow API", version = "2.0", description = "User Information"))//swagger
@SecurityScheme(name = "JWT Authentication", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP)//swagger
public class UserflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserflowApplication.class, args);

	}
	 
	 
	   
}
