package com.tos.timeoffserver.domain.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.tos.timeoffserver.domain.entites.ApplicationUser;
import com.tos.timeoffserver.domain.repositories.UserRepository;

public class CurrentUser {

	ApplicationUser applicationUser = new ApplicationUser();
	// singleton
	private static CurrentUser currentUser = new CurrentUser();
	@Autowired
	private String username;

	private CurrentUser() {
	}

	public static CurrentUser getInstance() {
		return currentUser;
	}

	public void setByUsername(String username) {
		System.out.println("------------setByUsername----------------------------" + username);
		this.username = username;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
