package com.vber.abank.deposit_service;

import org.springframework.boot.SpringApplication;

public class TestDepositServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(DepositServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
