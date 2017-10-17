package com.tos.timeoffserver;

import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tos.timeoffserver.domain.repositories")
@EntityScan(basePackages = { "com.tos.timeoffserver.domain.entites" })

public class TimeOffSystemTeamOneBackEndApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(TimeOffSystemTeamOneBackEndApplication.class, args);

	}
}