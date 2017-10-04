package com.tos.timeoffserver.domain.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String personalId;
	private String firstName;
	private String secondName;
	private String lastName;
	private String email;
	private String address;
	private String telephone;
	private String position;
	private Boolean isAdmin;
	private int PtoAvailable;
	private int PtoTotal;

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
		return PtoAvailable;
	}
	public int getPtoTotal() {
		return PtoTotal;
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
		PtoAvailable = ptoAvailable;
	}

	public void setPtoTotal(int ptoTotal) {
		PtoTotal = ptoTotal;
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

}
