package com.dream.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DreamServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DreamServiceApplication.class, args);
	}
}
