package com.test.controller;

import com.test.exception.UserNotFoundException;
import com.test.model.Post;
import com.test.model.User;
import com.test.repo.PostRepository;
import com.test.repo.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
public class UserJpaController {
    private UserRepository repo;

    private PostRepository postRepo;

    public UserJpaController(UserRepository repo, PostRepository postRepo) {
        this.repo = repo;
        this.postRepo = postRepo;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAll() {
        return repo.findAll();
    }

    //HATEOS Implementation
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findUser(@PathVariable int id) {
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

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostForUser(@PathVariable int id) {
        Optional<User> user = repo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found for the id:" + id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = repo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found for the id:" + id);
        }
        post.setUser(user.get());
        Post p = postRepo.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //details of specific post
    @GetMapping("/jpa/users/{id}/posts/{pid}")
    public Post retrievePostForUserwithpostid(@PathVariable int id,
                                              @PathVariable int pid) {
        Optional<User> user = repo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found for the id:" + id);
        }
        List<Post> p = user.get().getPosts();
        /*for (Post p1 : p) {
            if (p1.getId() == pid) {
                return p1;
            }
        }*/
        Predicate<? super Post> predicate = p1->p1.getId().equals(pid);
        return p.stream().filter(predicate).findFirst().get();
    }
}
