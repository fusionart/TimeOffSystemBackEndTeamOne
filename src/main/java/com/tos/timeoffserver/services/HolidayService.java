package com.tos.timeoffserver.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
}
