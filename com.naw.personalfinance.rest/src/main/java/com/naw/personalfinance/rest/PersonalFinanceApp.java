package com.naw.personalfinance.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.naw.personalfinance")
public class PersonalFinanceApp {
	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceApp.class, args);
	}
}
