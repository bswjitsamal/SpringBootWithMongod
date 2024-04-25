package com.programming.techie.expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {
	@Bean
	public OpenAPI expenseAPI() {
	return new OpenAPI()
	.info(new Info().title("Expense Tracker API")
	.description("Expense Tracker Application")
	.version("v0.0.1")
	.license(new License().name("Apache 2.0").url("https://expensetracker.org")))
	.externalDocs(new ExternalDocumentation()
	.description("Expense Tracker Wiki Documentation")
	.url("https://expensetracker.wiki/docs"));
	}
}
