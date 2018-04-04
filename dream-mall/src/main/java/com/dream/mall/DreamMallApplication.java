package com.dream.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DreamMallApplication {
	public static void main(String[] args) {
		SpringApplication.run(DreamMallApplication.class, args);
	}
}
