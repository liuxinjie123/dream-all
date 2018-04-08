package com.dream.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class DreamScheduleApplication {
	public static void main(String[] args) {
		SpringApplication.run(DreamScheduleApplication.class, args);
	}
}
