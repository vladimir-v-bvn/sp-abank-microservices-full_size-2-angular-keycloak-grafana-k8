package com.vber.abank.crdcard_service;

import org.springframework.boot.SpringApplication;

public class TestCrdcardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(CrdcardServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
