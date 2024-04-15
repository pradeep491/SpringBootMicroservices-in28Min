package com.test.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = service.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					  .path("/{id}").buildAndExpand(savedUser.getId())
					  .toUri();
		return ResponseEntity.created(location).build();
	}

	/*
	 * @PostMapping("/users") public void createUser(@RequestBody User user) {
	 * service.saveUser(user); }
	 */
}
