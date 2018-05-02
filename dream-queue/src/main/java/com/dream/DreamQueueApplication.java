package com.dream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class DreamQueueApplication {
	public static void main(String[] args) {
		SpringApplication.run(DreamQueueApplication.class, args);
	}
}
