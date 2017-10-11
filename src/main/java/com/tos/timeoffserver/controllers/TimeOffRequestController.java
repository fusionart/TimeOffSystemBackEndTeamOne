package com.tos.timeoffserver.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import com.tos.timeoffserver.domain.entites.TimeOffRequest;
import com.tos.timeoffserver.domain.repositories.TimeOffRequestRepository;
import com.tos.timeoffserver.services.TimeOffRequestService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class TimeOffRequestController {
	@Autowired
	private TimeOffRequestRepository requestRepository;
	
	private TimeOffRequestService requestSerice;

	@GetMapping(path = "/new_request")
	public @ResponseBody String addNewUser(@RequestParam String type, @RequestParam String startDate, @RequestParam String finishDate,
			@RequestParam String reason, @RequestParam String note) {
		java.sql.Date sqlCurrentDate = new java.sql.Date(new Date().getTime());
		TimeOffRequest newRequest = new TimeOffRequest();
		newRequest.setDateOfSubmit(sqlCurrentDate);
		newRequest.setDateStart(requestSerice.getStartDate(startDate));
		newRequest.setDateFinish(requestSerice.getFinishDate(finishDate));
		newRequest.setDays(requestSerice.getTimeOffDays(startDate, finishDate));
		newRequest.setType(type);
		newRequest.setReason(reason);
		newRequest.setNote(note);
		newRequest.setStatus("unapproved");
		requestRepository.save(newRequest);
		return "Added";
	}

	@GetMapping(path = "/list")
	public @ResponseBody Iterable<TimeOffRequest> getAllRequest() {
		return requestRepository.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TimeOffRequest> deleteTimeOffRequestById(@PathVariable(value = "id") Long id) {
		TimeOffRequest request = requestRepository.findOne(id);
		if (request == null) {
			return ResponseEntity.notFound().build();
		}
		requestRepository.delete(request);
		return ResponseEntity.ok().build();
	}

}