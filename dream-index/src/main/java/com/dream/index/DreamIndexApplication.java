package com.dream.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DreamIndexApplication {
	public static void main(String[] args) {
		SpringApplication.run(DreamIndexApplication.class, args);
	}
}
