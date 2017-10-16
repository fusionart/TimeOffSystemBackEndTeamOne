package com.tos.timeoffserver.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tos.timeoffserver.controllers.TimeOffRequestController.ChangeRequestStatusPost;
import com.tos.timeoffserver.domain.entites.Holiday;
import com.tos.timeoffserver.domain.entites.TimeOffRequest;
import com.tos.timeoffserver.domain.entites.User;
import com.tos.timeoffserver.domain.repositories.UserRepository;
import com.tos.timeoffserver.domain.model.LoginData;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	UserRepository userRepo;

	@GetMapping("/add")
	public @ResponseBody User addNewUser(@RequestParam String firstName, @RequestParam String secondName, @RequestParam String lastName,
			@RequestParam String address, @RequestParam String email, @RequestParam String personalId, @RequestParam String telephone, @RequestParam String position) {
		User user = new User();
		user.setFirstName(firstName);
		user.setSecondName(secondName);
		user.setLastName(lastName);
		user.setAddress(address);
		user.setEmail(email);
		user.setPersonalId(personalId);
		user.setTelephone(telephone);
		user.setPosition(position);
		user.setIsAdmin(false);
		userRepo.save(user);
		return user;
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/delete")
	public @ResponseBody void deleteUser(@RequestParam Long id) {
		userRepo.delete(id);
	}

	@GetMapping("/delete-all")
	public @ResponseBody void deleteAllUser() {
		userRepo.deleteAll();
	}
	
	@GetMapping("/get-user")
	public @ResponseBody User getUser(@RequestParam Long id) {
		return userRepo.findOne(id);
	}
	
	
	
//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<String> userAuthentication(@RequestBody LoginData loginData, HttpServletRequest request, HttpServletResponse respone) {
//		ArrayList<User> users = (ArrayList<User>) userRepo.findAll();
//		boolean isUserExist = false;
//		long userId;
//		System.out.println(loginData.getUsername());
//
//		for (User user: users) {
//			System.out.println(user.getUsername());
//			if (user.getUsername().equals(loginData.getUsername())) {
//				isUserExist = true;
//				userId = user.getId();
//				System.out.println("Found user!");
//			}
//		}
//		if (!isUserExist) {
//			System.out.println("User didn't exist");
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		}
//		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public @ResponseBody User loginUser(@RequestBody LoginData loginData) {
		System.out.println(" -------------------- loginUser ----------------------");
		ArrayList<User> users = (ArrayList<User>) userRepo.findAll();
		boolean isUserExist = false;
		long userId;
		System.out.println(loginData.getUsername());

		for (User user: users) {
			System.out.println("Check for: " + user.getUsername() + " - " + user.getPassword());
			if (user.getUsername().equals(loginData.getUsername()) && user.getPassword().equals(loginData.getPassword())) {
				isUserExist = true;
				userId = user.getId();
				System.out.println("Found user!");
				System.out.println(user.getEmail() + ", " + user.getFirstName() + ", " + user.getIsAdmin() + ", " + user.getUsername() + ", " + user.getPassword());
				return user;
			}
		}
		System.out.println("User not found!!!");
		return null;
	}
	

//	
//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<String> loginUser (@RequestBody LoginData, HttpServletRequest request, HttpServletResponse respone) {
//		ArrayList<User> users = (ArrayList<User>) userRepo.findAll();
//		boolean isUserExist = false;
//		long userId;
//		System.out.println(payload);
//
//		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//	}
	

//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//	public ResponseEntity<String> approveRequest(@RequestBody LoginData loginData) {
//		System.out.println(loginData.username);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//	}
//	

}
