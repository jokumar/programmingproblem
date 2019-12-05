package com.phorest.salon.clientservices.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.phorest.salon.clientservices.jpa.repository")
@EntityScan(basePackages = "com.phorest.salon")
public class ClientServicesConfig {

}
