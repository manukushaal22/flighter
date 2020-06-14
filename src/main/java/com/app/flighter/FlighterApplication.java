package com.app.flighter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FlighterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlighterApplication.class, args);
	}

}
