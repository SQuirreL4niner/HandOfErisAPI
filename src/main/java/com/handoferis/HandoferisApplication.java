package com.handoferis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("application.yml")
public class HandoferisApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandoferisApplication.class, args);
	}
}
