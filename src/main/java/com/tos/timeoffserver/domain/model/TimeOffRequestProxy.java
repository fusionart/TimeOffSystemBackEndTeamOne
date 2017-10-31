package com.tos.timeoffserver.domain.model;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.tos.timeoffserver.domain.entites.ApplicationUser;
import com.tos.timeoffserver.domain.entites.TimeOffRequest;
import com.tos.timeoffserver.domain.repositories.TimeOffRequestRepository;
import com.tos.timeoffserver.domain.repositories.UserRepository;
import com.tos.timeoffserver.services.TimeOffRequestService;



public class TimeOffRequestProxy {
	@Autowired
	private TimeOffRequestService timeOffRequestService;
	
	private Long id;
	private Date dateOfSubmit;
	private String type;
	private int days;
	private Date dateStart;
	private Date dateFinish;
	private String status;
	private String reason;
	private String note;
	private String allDates;
	
	public void entityToResponse (TimeOffRequest timeOffRequestEntity) {
		this.id = timeOffRequestEntity.getId();
		this.dateOfSubmit = timeOffRequestEntity.getDateOfSubmit();
		this.type = timeOffRequestEntity.getType();
		this.days = timeOffRequestEntity.getDays();
		this.dateStart = timeOffRequestEntity.getDateStart();
		this.dateFinish = timeOffRequestEntity.getDateFinish();
		this.status = timeOffRequestEntity.getStatus();
		this.reason = timeOffRequestEntity.getReason();
		this.note = timeOffRequestEntity.getNote();
		this.allDates = timeOffRequestEntity.getDateStart() + " - " + timeOffRequestEntity.getDateFinish();
//		this.allDates = timeOffRequestService.getDates(timeOffRequestEntity.getDateStart(), timeOffRequestEntity.getDateStart());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOfSubmit() {
		return dateOfSubmit;
	}

	public void setDateOfSubmit(Date dateOfSubmit) {
		this.dateOfSubmit = dateOfSubmit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAllDates() {
		return allDates;
	}

	public void setAllDates(String allDates) {
		this.allDates = allDates;
	}
	
}
