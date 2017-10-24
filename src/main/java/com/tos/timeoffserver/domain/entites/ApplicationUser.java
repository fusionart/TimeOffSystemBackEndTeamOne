package com.tos.timeoffserver.domain.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class ApplicationUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String personalId;
	private String firstName;
	private String secondName;
	private String lastName;
	private String email;
	private String address;
	private String telephone;
	private String position;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean isAdmin;
	private int ptoAvailable;
	private int ptoTotal;

	// @ManyToMany(cascade = CascadeType.ALL)
	// @JoinTable(name = "request_map", joinColumns = {
	// @JoinColumn(name = "user_id", referencedColumnName = "id") },
	// inverseJoinColumns = {
	// @JoinColumn(name = "request_id", referencedColumnName = "id") })
	// private Set<TimeOffRequest> requests = new HashSet<TimeOffRequest>(0);

	// public ApplicationUser() {
	// }

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<TimeOffRequest> requests;

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public Long getId() {
		return id;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPersonalId() {
		return personalId;
	}

	public String getPosition() {
		return position;
	}

	public int getPtoAvailable() {
		return ptoAvailable;
	}

	public int getPtoTotal() {
		return ptoTotal;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setPtoAvailable(int ptoAvailable) {
		ptoAvailable = ptoAvailable;
	}

	public void setPtoTotal(int ptoTotal) {
		ptoTotal = ptoTotal;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TimeOffRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<TimeOffRequest> requests) {
		this.requests = requests;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
