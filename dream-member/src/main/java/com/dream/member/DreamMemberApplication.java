package com.dream.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DreamMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamMemberApplication.class, args);
	}
}
