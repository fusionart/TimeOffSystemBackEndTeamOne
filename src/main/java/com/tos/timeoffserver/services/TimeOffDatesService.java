package com.tos.timeoffserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tos.timeoffserver.domain.repositories.HolidayRepository;

@Service
public class TimeOffDatesService {
	@Autowired
	public HolidayRepository holidayRepository;

}
