package com.tos.timeoffserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tos.timeoffserver.domain.entites.User;
import com.tos.timeoffserver.domain.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public boolean isUserAdmin (User currentUser) {
		boolean isAdmin = currentUser.getIsAdmin();
		return isAdmin;
		
	}

}
