package com.tos.timeoffserver.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.tos.timeoffserver.domain.entites.ApplicationUser;
import com.tos.timeoffserver.domain.entites.TimeOffRequest;

public class UserResponse {

	private Long userId;
	private String firstName;
	private String secondName;
	private String lastName;
	private String position;
	private String telephone;
	private int ptoTotal;
	private int ptoAvailable;
	private String address;
	private String email;
	private boolean isAdmin;
	private List<TimeOffRequestProxy> userRequests;

	public void entityToResponse(ApplicationUser userEntity) {
		this.userId = userEntity.getId();
		this.firstName = userEntity.getFirstName();
		this.secondName = userEntity.getSecondName();
		this.lastName = userEntity.getLastName();
		this.position = userEntity.getPosition();
		this.telephone = userEntity.getTelephone();
		this.ptoTotal = userEntity.getPtoTotal();
		this.ptoAvailable = userEntity.getPtoAvailable();
		this.email = userEntity.getEmail();
		this.address = userEntity.getAddress();
		this.isAdmin = userEntity.getIsAdmin();
		this.userRequests = getTimeOffRequestProxy(userEntity.getRequests());
	}

	private List<TimeOffRequestProxy> getTimeOffRequestProxy(List<TimeOffRequest> requests) {
		List<TimeOffRequestProxy> requestList = new ArrayList<TimeOffRequestProxy>();
		for (TimeOffRequest request : requests) {
			TimeOffRequestProxy timeOffRequestProxy = new TimeOffRequestProxy();
			timeOffRequestProxy.entityToResponse(request);
			requestList.add(timeOffRequestProxy);
		}
		return requestList;
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

	public List<TimeOffRequestProxy> getUserRequests() {
		return userRequests;
	}

	public void setUserRequests(List<TimeOffRequestProxy> userRequests) {
		this.userRequests = userRequests;
	}

	public int getPtoAvailable() {
		return ptoAvailable;
	}

	public void setPtoAvailable(int ptoAvailable) {
		this.ptoAvailable = ptoAvailable;
	}

}