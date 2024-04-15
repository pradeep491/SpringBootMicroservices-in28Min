package com.test.controller;

import java.net.URI;
import java.util.List;

import com.test.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
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
        User user = service.findUser(id);
        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saveUser = service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saveUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /*
     * @PostMapping("/users") public void createUser(@RequestBody User user) {
     * service.saveUser(user); }
     */
	/*@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = service.deleteUser(id);
		if(user == null){
			throw new UserNotFoundException("id:"+id);
		}
		return user;
	}*/
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return "user deleted successfully - id:" + id;
    }
}
