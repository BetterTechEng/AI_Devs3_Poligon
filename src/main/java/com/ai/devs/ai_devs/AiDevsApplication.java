package com.ai.devs.ai_devs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiDevsApplication {

	public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled", "true");
//		System.setProperty("spring.devtools.restart.poll-interval", "1s");
//		System.setProperty("spring.devtools.restart.quiet-period", "1s");

		SpringApplication.run(AiDevsApplication.class, args);
	}

}
