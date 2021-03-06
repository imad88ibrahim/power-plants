package com.test.powerplants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class PowerPlantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerPlantsApplication.class, args);
	}

}
