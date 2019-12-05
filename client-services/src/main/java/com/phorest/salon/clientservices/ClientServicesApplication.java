package com.phorest.salon.clientservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.phorest.salon.clientservices.config.ClientServicesConfig;

/**
 * Main Spring Boot Application
 *
 */
@SpringBootApplication
@Import({ClientServicesConfig.class})
public class ClientServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServicesApplication.class, args);
	}

}
