package com.zephsie.securityNew;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class SecurityNewApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecurityNewApplication.class, args);
	}

	@Bean
	public CommandLineRunner preloadData() {
		return args -> log.info("Preloading data");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}