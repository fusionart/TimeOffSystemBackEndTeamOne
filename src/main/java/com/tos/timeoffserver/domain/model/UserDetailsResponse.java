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
		
	
	public UserDetailsResponse(ApplicationUser user) {
		super();
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