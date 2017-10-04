package com.tos.timeoffserver.domain.entites;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimeOffRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date dateOfSubmit;
	private String type;
	private int days;
	private Date dateStart;
	private Date dateFinish;
	private String status;
	private String reason;
	private String note;
	
	public Date getDateFinish() {
		return dateFinish;
	}
	public Date getDateOfSubmit() {
		return dateOfSubmit;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public int getDays() {
		return days;
	}
	public Long getId() {
		return id;
	}
	public String getNote() {
		return note;
	}
	public String getReason() {
		return reason;
	}
	public String getStatus() {
		return status;
	}
	public String getType() {
		return type;
	}
	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}
	public void setDateOfSubmit(Date dateOfSubmit) {
		this.dateOfSubmit = dateOfSubmit;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
