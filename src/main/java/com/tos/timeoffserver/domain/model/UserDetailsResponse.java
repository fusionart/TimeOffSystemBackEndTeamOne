package com.tos.timeoffserver.domain.model;

import java.util.HashMap;
import java.util.Map;

import com.tos.timeoffserver.domain.entites.ApplicationUser;

public class UserDetailsResponse {
	private Long userId;
	private String firstName;
	private String secondName;
	private String lastName;
	private String position;
	private String telephone;
	private int ptoTotal;
	private String address;
	private String email;
	private boolean isAdmin;
		
	
	public void modelToResponse (ApplicationUser user) {
		this.userId = user.getId();
		this.firstName = user.getFirstName();
		this.secondName = user.getSecondName();
		this.lastName = user.getLastName();
		this.position = user.getPosition();
		this.telephone = user.getTelephone();
		this.ptoTotal = user.getPtoTotal();
		this.email = user.getEmail();
		this.address = user.getAddress();
		this.isAdmin = user.getIsAdmin();
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getSecondName() {
		return secondName;
	}


	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public int getPtoTotal() {
		return ptoTotal;
	}


	public void setPtoTotal(int ptoTotal) {
		this.ptoTotal = ptoTotal;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


//	public Map<String, Object> getUserDetails() {
//		HashMap<String, Object> userDetails = new HashMap<>();
//		userDetails.put("firstName:", this.firstName);
//		userDetails.put("secondName:", this.secondName);
//		userDetails.put("lastName:", this.lastName);
//		userDetails.put("position:", this.position);
//		userDetails.put("telephone:", this.telephone);
//		userDetails.put("userId:", this.userId);
//		userDetails.put("ptoTotal:", this.ptoTotal);
//		userDetails.put("address:", this.address);
//		userDetails.put("email:", this.email);
//		userDetails.put("isAdmin:", this.isAdmin);
//		return userDetails;
//	}
}