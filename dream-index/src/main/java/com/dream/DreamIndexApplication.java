package com.dream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DreamIndexApplication {
	public static void main(String[] args) {
		SpringApplication.run(DreamIndexApplication.class, args);
	}
}
