package com.tos.timeoffserver;

import java.text.ParseException;
import com.tos.timeoffserver.services.HolidayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import javax.transaction.Transactional;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tos.timeoffserver.domain.repositories")
@EntityScan( basePackages = {"com.tos.timeoffserver.domain.entites"} )


public class TimeOffSystemTeamOneBackEndApplication {
//	@Autowired
//	public HolidayService holidays;
	
	public static void main(String[] args) throws ParseException {
		SpringApplication.run(TimeOffSystemTeamOneBackEndApplication.class, args);
//		holidays.initDb();
		
		
	}
}