package com.tos.timeoffserver.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;

import com.tos.timeoffserver.domain.repositories.HolidayRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

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
	public void initDb() throws ParseException{
		System.out.println("------------------ = 2 ==========================");
		addHoliday("2017-12-24");
		addHoliday("2017-12-25");
		addHoliday("2017-12-26");
	}
	
	public void justDoNothing(String date) throws ParseException {
		
		Holiday newHoliday = new Holiday();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate;
		
			parsedDate = format.parse(date);
			Date sqlDate = new Date(parsedDate.getTime());
			newHoliday.setDate(sqlDate);
			
			holidayRepository.save(newHoliday);
			System.out.println(sqlDate);

	}
}
