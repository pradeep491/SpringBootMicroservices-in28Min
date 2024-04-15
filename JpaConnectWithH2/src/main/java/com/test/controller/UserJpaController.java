package com.test.controller;

import com.test.exception.UserNotFoundException;
import com.test.model.User;
import com.test.repo.UserRepository;
import com.test.service.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {

    private UserDaoService service;

    private UserRepository repo;

    public UserJpaController(UserDaoService service, UserRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAll() {
        return repo.findAll();
    }

    //HATEOS Implementation
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findUser(@PathVariable int id) {
        //User user = service.findUser(id);
        Optional<User> user = repo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAll());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saveUser = repo.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(saveUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public String deleteUser(@PathVariable int id) {
        repo.deleteById(id);
        return "user deleted successfully - id:" + id;
    }
}
