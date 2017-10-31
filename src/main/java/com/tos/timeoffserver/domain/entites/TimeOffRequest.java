package com.tos.timeoffserver.domain.entites;



import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class TimeOffRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private Date dateOfSubmit;
	@NotNull
	private String type;
	@NotNull
	private int days;
	@NotNull
	private Date dateStart;
	@NotNull
	private Date dateFinish;
	@NotNull
	private String dates;
	@NotNull
	private String status;
	private String reason;
	private String note;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "request", fetch = FetchType.LAZY)
	private List<TimeOffDate> timeOffDates;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private ApplicationUser user;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "document_map", joinColumns = {
			@JoinColumn(name = "request_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "document_id", referencedColumnName = "document_id") })
	private Set<Document> documents = new HashSet<Document>(0);

	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

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

	public void setDateStart(Date date) {
		this.dateStart = date;
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

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public List<TimeOffDate> getTimeOffDates() {
		return timeOffDates;
	}

	public void setTimeOffDates(List<TimeOffDate> timeOffDates) {
		this.timeOffDates = timeOffDates;
	}
	
}
