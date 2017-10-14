package com.tos.timeoffserver.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tos.timeoffserver.domain.entites.Holiday;
import com.tos.timeoffserver.domain.entites.User;
import com.tos.timeoffserver.domain.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public boolean isUserAdmin(User currentUser) {
		boolean isAdmin = currentUser.getIsAdmin();
		return isAdmin;

	}

	public void addUser(String firstName, String secondName, String lastName, String username, String email,
			String address, String telephone, String position, boolean isAdmin, int PtoAvailable, int PtoTotal) {
		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setSecondName(secondName);
		newUser.setLastName(lastName);
		newUser.setUsername(username);
		newUser.setEmail(email);
		newUser.setAddress(address);
		newUser.setTelephone(telephone);
		newUser.setPosition(position);
		newUser.setIsAdmin(isAdmin);
		newUser.setPersonalId(firstName + " " + lastName);
		newUser.setPtoAvailable(PtoAvailable);
		newUser.setPtoTotal(PtoTotal);
		userRepository.save(newUser);
	}

	@PostConstruct
	public void initDb() throws ParseException {
		ArrayList<User> holydays = (ArrayList<User>) userRepository.findAll();
		if (holydays.size() < 2) {
			addUser("Kiril", "Mihailov", "Kotev", "kiril_mk", "kiril.kotev@gmail.com", "Vratsa, bul.Mito Orozov 14",
					"088 852 0822", "administrator", true, 8, 24);
			addUser("Ivan", "Petkov", "Georgiev", "ivan_gp", "ivan_georgiev@gmail.com", "Vratsa, bul.Hristo Botev 24",
					"088 874 0841", "draftsman", false, 14, 20);
			addUser("Kalina", "Kalinova", "Savova", "kalina_ks", "kalina_savova@gmail.com", "Vratsa, ul.Morava 18",
					"088 874 0841", "secretary", false, 5, 22);
		}
	}
}
