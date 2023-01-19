package com.mauricio.gasolineservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GasolineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GasolineServiceApplication.class, args);
	}

}
