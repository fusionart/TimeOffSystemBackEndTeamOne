package com.tos.timeoffserver.services;

import java.text.ParseException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tos.timeoffserver.domain.entites.ApplicationUser;
import com.tos.timeoffserver.domain.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public boolean isUserAdmin(ApplicationUser currentUser) {
		boolean isAdmin = currentUser.getIsAdmin();
		return isAdmin;

	}

	public void addUser(String firstName, String secondName, String lastName, String username, String password,
			String email, String address, String telephone, String position, boolean isAdmin, int PtoAvailable,
			int PtoTotal) {
		ApplicationUser newUser = new ApplicationUser();
		newUser.setFirstName(firstName);
		newUser.setSecondName(secondName);
		newUser.setLastName(lastName);
		newUser.setUsername(username);
		newUser.setPassword(password);
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

	public void changeUserProAvailable(String type, int days, ApplicationUser currentUser) {
		if (type.equals("PTO")) {
			ApplicationUser userToUpdate = userRepository.findOne(currentUser.getId());
			System.out.println("findone: " + currentUser.getId());
			System.out.println("type: " + type + "; days: " + days + "; user: " + currentUser.getPtoAvailable());
			userToUpdate.setPtoAvailable(currentUser.getPtoAvailable() - days);
			userRepository.save(userToUpdate);
		}

	}

	@PostConstruct
	public void initDb() throws ParseException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		ArrayList<ApplicationUser> holydays = (ArrayList<ApplicationUser>) userRepository.findAll();
		if (holydays.size() < 2) {
			addUser("Kiril", "Mihailov", "Kotev", "admin", bCryptPasswordEncoder.encode("123456"),
					"kiril.kotev@gmail.com", "Vratsa, bul.Mito Orozov 14", "088 852 0822", "administrator", true, 0,
					24);
			addUser("Ivan", "Petkov", "Georgiev", "ivan_gp", bCryptPasswordEncoder.encode("123456"),
					"ivan_georgiev@gmail.com", "Vratsa, bul.Hristo Botev 24", "088 874 0841", "draftsman", false, 0,
					20);
			addUser("Kalina", "Kalinova", "Savova", "kalina_ks", bCryptPasswordEncoder.encode("123456"),
					"kalina_savova@gmail.com", "Vratsa, ul.Morava 18", "088 874 0841", "secretary", false, 0, 22);
		}
	}
}
