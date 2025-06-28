package com.jogajunto.JogaJunto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JogaJuntoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JogaJuntoApplication.class, args);
	}

}
