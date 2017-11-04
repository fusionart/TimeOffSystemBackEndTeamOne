package com.tos.timeoffserver.domain.model;

import java.sql.Date;

public class NewTimeOffRequestBody {
	private Date dateOfSubmit;
	private String type;
	private int days;
	private Date dateStart;
	private Date dateFinish;
	private String dates;
	private String status;
	private String reason;
	private String note;
	private Date[] selectedDays;

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

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
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

	public Date[] getSelectedDays() {
		return selectedDays;
	}

	public void setSelectedDays(Date[] selectedDays) {
		this.selectedDays = selectedDays;
	}

}
