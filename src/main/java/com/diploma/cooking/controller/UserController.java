package com.diploma.cooking.controller;

import com.diploma.cooking.model.User;
import com.diploma.cooking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return users.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        return user.getId() == 0
                ? new ResponseEntity<>(userService.saveOrUpdate(user), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@RequestBody User user) {
        return user.getId() > 0
                ? new ResponseEntity<>(userService.saveOrUpdate(user), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<User> delete(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    userService.delete(user);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}
