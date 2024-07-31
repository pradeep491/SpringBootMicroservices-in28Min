package com.test.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.service.UserDaoService;

@RestController
public class UserController {

	private UserDaoService service;

	public UserController(UserDaoService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> retrieveAll() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User findUser(@PathVariable int id) {
		return service.findUser(id);

	}
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return service.saveUser(user);
	}
}
