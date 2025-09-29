package com.vber.abank.crdcard_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CrdcardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrdcardServiceApplication.class, args);
	}

}
