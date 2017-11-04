package com.tos.timeoffserver.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tos.timeoffserver.domain.entites.ApplicationUser;
import com.tos.timeoffserver.domain.repositories.UserRepository;

import com.tos.timeoffserver.domain.model.LoginData;
import com.tos.timeoffserver.domain.model.UserDetailsResponse;
import com.tos.timeoffserver.domain.model.UserRequest;
import com.tos.timeoffserver.domain.model.UserResponse;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private UserRepository userRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepo = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/sign-up")
	public void signUp(@RequestBody ApplicationUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/user-info")
	UserDetailsResponse getUserInfo(@RequestBody UserRequest userInfoRequest) {
		ApplicationUser requestedUser = userRepo.findByUsername(userInfoRequest.getUsername());
		UserDetailsResponse userInfo = new UserDetailsResponse();
		userInfo.modelToResponse(requestedUser);
		return userInfo;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/add")
	public @ResponseBody ApplicationUser addNewUser(@RequestParam String firstName, @RequestParam String secondName,
			@RequestParam String lastName, @RequestParam String address, @RequestParam String email,
			@RequestParam String personalId, @RequestParam String telephone, @RequestParam String position) {
		ApplicationUser user = new ApplicationUser();
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
	public @ResponseBody Iterable<ApplicationUser> getAllUsers() {
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
	public @ResponseBody UserResponse getUser(@RequestParam Long id) {
		UserResponse getUserResponse = new UserResponse();
		getUserResponse.entityToResponse(userRepo.findOne(id));
		return getUserResponse;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public @ResponseBody ApplicationUser loginUser(@RequestBody LoginData loginData) {
		ArrayList<ApplicationUser> users = (ArrayList<ApplicationUser>) userRepo.findAll();
		boolean isUserExist = false;
		long userId;
		for (ApplicationUser user : users) {
			if (user.getUsername().equals(loginData.getUsername())
					&& user.getPassword().equals(loginData.getPassword())) {
				isUserExist = true;
				userId = user.getId();
				return user;
			}
		}
		return null;
	}

}
