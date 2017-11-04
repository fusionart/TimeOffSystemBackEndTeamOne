package com.tos.timeoffserver.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tos.timeoffserver.domain.repositories.HolidayRepository;

import com.tos.timeoffserver.domain.entites.Holiday;

@Service
public class HolidayService {
	@Autowired
	public HolidayRepository holidayRepository;

	public void addHoliday(String dateString) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = format.parse(dateString);
		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
		Holiday newHoliday = new Holiday();
		newHoliday.setDate(sqlDate);
		holidayRepository.save(newHoliday);
	}

	@PostConstruct
	public void initDb() throws ParseException {
		ArrayList<Holiday> holydays = (ArrayList<Holiday>) holidayRepository.findAll();
		if (holydays.size() < 1) {
			addHoliday("2017-12-24");
			addHoliday("2017-12-25");
			addHoliday("2017-12-26");
			addHoliday("2018-01-01");
			addHoliday("2018-03-05");
			addHoliday("2018-04-06");
			addHoliday("2018-04-09");
			addHoliday("2018-05-01");
			addHoliday("2018-05-07");
			addHoliday("2018-05-24");
			addHoliday("2018-09-06");
			addHoliday("2018-09-24");
			addHoliday("2018-12-24");
			addHoliday("2018-12-25");
			addHoliday("2018-12-26");
			addHoliday("2019-01-01");
			addHoliday("2019-03-04");
		}
	}

}
