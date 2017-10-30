package com.tos.timeoffserver.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import com.tos.timeoffserver.domain.entites.TimeOffRequest;
import com.tos.timeoffserver.domain.model.CurrentUser;
import com.tos.timeoffserver.domain.model.TimeOffRequestProxy;
import com.tos.timeoffserver.domain.model.TimeOffRequestResponse;
import com.tos.timeoffserver.domain.repositories.TimeOffRequestRepository;
import com.tos.timeoffserver.domain.repositories.UserRepository;
import com.tos.timeoffserver.security.JWTAuthorizationFilter;
import com.tos.timeoffserver.services.TimeOffRequestService;
import com.tos.timeoffserver.services.UserService;
import com.tos.timeoffserver.domain.entites.ApplicationUser;

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
	private CurrentUser currentUser = CurrentUser.getInstance( );
	// TimeOffRequest newRequest = new TimeOffRequest();

	// @PostMapping(path = "/new_request")
	// public @ResponseBody String addNewRequest(@RequestParam String typeOff,
	// @RequestParam String startDate,
	// @RequestParam String finishDate, @RequestParam String reason, @RequestParam
	// String note) {
	// java.sql.Date sqlCurrentDate = new java.sql.Date(new Date().getTime());
	// TimeOffRequest newRequest = new TimeOffRequest();
	// newRequest.setDateOfSubmit(sqlCurrentDate);
	// newRequest.setDateStart(requestSerice.getStartDate(startDate));
	// newRequest.setDateFinish(requestSerice.getFinishDate(finishDate));
	// newRequest.setDays(requestSerice.getTimeOffDays(startDate, finishDate));
	// newRequest.setType(typeOff);
	// newRequest.setReason(reason);
	// newRequest.setNote(note);
	// newRequest.setStatus("unapproved");
	// requestRepository.save(newRequest);
	// return "Added";
	// }

	@RequestMapping(value = "/new_request", method = RequestMethod.POST)
	public @ResponseBody String addNewRequest(@RequestBody TimeOffRequest timeOffRequest, HttpServletRequest req) {
		java.sql.Date sqlCurrentDate = new java.sql.Date(new Date().getTime());
		String username = JWTAuthorizationFilter.class.getName();
		TimeOffRequest newRequest = new TimeOffRequest();
		newRequest.setDateOfSubmit(sqlCurrentDate);
		newRequest.setDateStart(requestService.startDate(timeOffRequest.getDateStart(), timeOffRequest.getDateFinish()));
		newRequest.setDateFinish(requestService.finishDate(timeOffRequest.getDateStart(), timeOffRequest.getDateFinish()));
		//newRequest.setDays(requestSerice.getTimeOffDays(timeOffRequest.getDateStart(), timeOffRequest.getDateFinish()));
		newRequest.setDays(timeOffRequest.getDays());
		newRequest.setType(timeOffRequest.getType());
		newRequest.setReason(timeOffRequest.getReason());
		newRequest.setNote(timeOffRequest.getNote());
		newRequest.setStatus("unapproved");
		newRequest.setUser(userRepository.findByUsername(currentUser.getUsername()));
		requestRepository.save(newRequest);
		return "Added";
	}

	@GetMapping(value = "/request-list")
	public @ResponseBody Iterable<TimeOffRequestResponse> getAllRequest() {
		List<TimeOffRequest> requestEntitiesList = requestRepository.findAll();
		List<TimeOffRequestResponse> requestListResponse = new ArrayList<TimeOffRequestResponse>();
		for(TimeOffRequest requestEntity : requestEntitiesList) {
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