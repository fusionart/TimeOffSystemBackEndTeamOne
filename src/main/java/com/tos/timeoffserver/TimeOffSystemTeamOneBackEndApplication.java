package com.tos.timeoffserver;

import java.text.ParseException;
import com.tos.timeoffserver.services.HolidayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tos.timeoffserver.domain.repositories")
public class TimeOffSystemTeamOneBackEndApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(TimeOffSystemTeamOneBackEndApplication.class, args);
		HolidayService holidays = new HolidayService();
		holidays.addHoliday("2017-12-24");
		holidays.addHoliday("2017-12-25");
		holidays.addHoliday("2017-12-26");
		holidays.addHoliday("2017-12-31");
	}
}
