package com.tos.timeoffserver.domain.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.tos.timeoffserver.domain.entites.Holiday;
import com.tos.timeoffserver.domain.entites.TimeOffRequest;
import com.tos.timeoffserver.services.TimeOffRequestService;

public class TimeOffRequestResponse {
	
	private Long id;
	private Date dateOfSubmit;
	private String type;
	private int days;
	private Date dateStart;
	private Date dateFinish;
	private String status;
	private String reason;
	private String note;
	private String personalId;
	private String allDates;
	private String dates;



	public void entityToResponse(TimeOffRequest timeOffRequestEntity, TimeOffRequestService requestService) {
		this.id = timeOffRequestEntity.getId();
		this.dateOfSubmit = timeOffRequestEntity.getDateOfSubmit();
		this.type = timeOffRequestEntity.getType();
		this.days = timeOffRequestEntity.getDays();
		this.dateStart = timeOffRequestEntity.getDateStart();
		this.dateFinish = timeOffRequestEntity.getDateFinish();
		this.status = timeOffRequestEntity.getStatus();
		this.reason = timeOffRequestEntity.getReason();
		this.note = timeOffRequestEntity.getNote();
		this.personalId = timeOffRequestEntity.getUser().getPersonalId();
		this.allDates = requestService.getDates(getDateStart(), getDateFinish());
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

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getAllDates() {
		return allDates;
	}

	public void setAllDates(String allDates) {
		this.allDates = allDates;
	}

}
