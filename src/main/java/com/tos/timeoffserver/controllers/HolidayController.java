package com.tos.timeoffserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tos.timeoffserver.domain.entites.Holiday;
import com.tos.timeoffserver.domain.repositories.HolidayRepository;
import com.tos.timeoffserver.services.HolidayService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class HolidayController {
	@Autowired
	private HolidayRepository holidayRepository;
	@Autowired
	private HolidayService holidayService;
	
	@GetMapping(value = "/holiday-list")
	public @ResponseBody Iterable<Holiday> getAllHolidays() {
		return holidayRepository.findAll();
	}
}
