package com.tos.timeoffserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tos.timeoffserver.domain.entites.User;
import com.tos.timeoffserver.domain.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api")
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
}
