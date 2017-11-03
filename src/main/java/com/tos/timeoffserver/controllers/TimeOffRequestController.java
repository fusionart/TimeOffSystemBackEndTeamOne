package com.tos.timeoffserver.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.tos.timeoffserver.domain.entites.TimeOffRequest;
import com.tos.timeoffserver.domain.model.CurrentUser;
import com.tos.timeoffserver.domain.model.NewTimeOffRequestBody;
import com.tos.timeoffserver.domain.model.TimeOffRequestResponse;
import com.tos.timeoffserver.domain.repositories.TimeOffDatesRepository;
import com.tos.timeoffserver.domain.repositories.TimeOffRequestRepository;
import com.tos.timeoffserver.domain.repositories.UserRepository;
import com.tos.timeoffserver.services.TimeOffRequestService;
import com.tos.timeoffserver.services.UserService;
import com.tos.timeoffserver.utilities.DateUtility;
import com.tos.timeoffserver.domain.entites.ApplicationUser;
import com.tos.timeoffserver.domain.entites.TimeOffDate;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
public class TimeOffRequestController {
	@Autowired
	private TimeOffRequestRepository requestRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TimeOffRequestService requestService;
	@Autowired
	private UserService userSerice;
	@Autowired
	private TimeOffDatesRepository datesRepository;
	private CurrentUser currentUser = CurrentUser.getInstance();

	@RequestMapping(value = "/new_request", method = RequestMethod.POST)
	public @ResponseBody String addNewRequest(@RequestBody NewTimeOffRequestBody newTimeOffRequest,
			HttpServletRequest req) {
		java.sql.Date sqlCurrentDate = new java.sql.Date(new Date().getTime());
		DateUtility dateUtility = new DateUtility();
		TimeOffRequest timeOffRequest = new TimeOffRequest();
		timeOffRequest.setDateOfSubmit(sqlCurrentDate);
		timeOffRequest.setDateStart(
				dateUtility.getStartDate(newTimeOffRequest.getDateStart(), newTimeOffRequest.getDateFinish()));
		timeOffRequest.setDateFinish(
				dateUtility.getFinishDate(newTimeOffRequest.getDateStart(), newTimeOffRequest.getDateFinish()));
		timeOffRequest.setDays(newTimeOffRequest.getDays());
		timeOffRequest.setType(newTimeOffRequest.getType());
		timeOffRequest.setReason(newTimeOffRequest.getReason());
		timeOffRequest.setNote(newTimeOffRequest.getNote());
		timeOffRequest.setStatus("unapproved");
		timeOffRequest.setDates(dateUtility.getDates(newTimeOffRequest.getSelectedDays()));
		timeOffRequest.setUser(userRepository.findByUsername(currentUser.getUsername()));
		userSerice.changeUserProAvailable(newTimeOffRequest.getType(), newTimeOffRequest.getDays(),
				userRepository.findByUsername(currentUser.getUsername()));
		requestRepository.save(timeOffRequest);
		Date[] dates = newTimeOffRequest.getSelectedDays();
		for (Date date : dates) {
			TimeOffDate timeOffDates = new TimeOffDate();
			timeOffDates.setDate(date);
			timeOffDates.setRequest(timeOffRequest);
			datesRepository.save(timeOffDates);
		}
		return "Added";
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody String getStringDates(@RequestBody NewTimeOffRequestBody newTimeOffRequest,
			HttpServletRequest req) {
		DateUtility dateUtility = new DateUtility();
		String dates = dateUtility.getDates(newTimeOffRequest.getSelectedDays());
		System.out.println(dates);
		return "Added";
	}

	@GetMapping(value = "/request-list")
	public @ResponseBody Iterable<TimeOffRequestResponse> getAllRequest() {
		List<TimeOffRequest> requestEntitiesList = requestRepository.findAll();
		List<TimeOffRequestResponse> requestListResponse = new ArrayList<TimeOffRequestResponse>();
		for (TimeOffRequest requestEntity : requestEntitiesList) {
			TimeOffRequestResponse request = new TimeOffRequestResponse();
			request.entityToResponse(requestEntity, requestService);
			requestListResponse.add(request);
		}
		return requestListResponse;
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public ResponseEntity<String> approveRequest(@RequestBody ChangeRequestStatusPost changeStatusPost) {
		TimeOffRequest request = requestRepository.findOne(changeStatusPost.getRequestId());
		ApplicationUser currentUser = userRepository.findOne(changeStatusPost.getUserId());
		if (!userSerice.isUserAdmin(currentUser)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		if (request == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		requestService.approveRequest(request);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
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

	static class ChangeRequestStatusPost {
		Long userId;
		Long requestId;

		public Long getUserId() {
			return userId;
		}

		public Long getRequestId() {
			return requestId;
		}
	}
}