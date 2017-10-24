package com.tos.timeoffserver.domain.model;

import java.sql.Date;

import com.tos.timeoffserver.domain.entites.TimeOffRequest;

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
	
}