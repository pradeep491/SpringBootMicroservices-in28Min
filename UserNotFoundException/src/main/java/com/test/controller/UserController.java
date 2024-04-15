package com.test.controller;

import com.test.exception.UserNotFoundException;
import com.test.model.User;
import com.test.service.UserDaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User u = service.findOne(id);
        if (u == null) {
            throw new UserNotFoundException("User not found for the id:" + id);
        }
        return u;
    }

    @PostMapping("/users")
    public User findAll(@RequestBody User user) {
        return service.createUser(user);
    }
}
