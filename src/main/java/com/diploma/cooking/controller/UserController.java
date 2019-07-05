package com.diploma.cooking.controller;

import com.diploma.cooking.model.User;
import com.diploma.cooking.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final DishService dishService;
    private final LikeService likeService;
    private final CommentService commentService;
    private final StorageService storageService;

    public UserController(UserService userService, DishService dishService, LikeService likeService, CommentService commentService, StorageService storageService) {
        this.userService = userService;
        this.dishService = dishService;
        this.likeService = likeService;
        this.commentService = commentService;
        this.storageService = storageService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return users.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> save(@RequestBody User user) {
        return user.getId() == 0
                ? new ResponseEntity<>(userService.saveOrUpdate(user), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/users")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> update(@RequestBody User user) {
        return user.getId() > 0
                ? new ResponseEntity<>(userService.saveOrUpdate(user), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    userService.delete(user);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/users/username/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        return userService.findByUsername(username)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/users/email/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> findByEmail(@PathVariable String email){
        return userService.findByEmail(email)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/users/exist-username/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.existsByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/users/exist-email/{email}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.existsByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/users/storage/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<User>> findByStorage(@PathVariable Long id){
        return storageService.findById(id)
                .map(storage -> new ResponseEntity<>(userService.findByStorage(storage), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/users/favorite-dish/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<User>> findByFavoriteDish(@PathVariable Long id){
        return dishService.findById(id)
                .map(dish -> new ResponseEntity<>(userService.findByFavouriteDish(dish), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/users/authored-dish/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> findByAuthoredDish(@PathVariable Long id){
        return dishService.findById(id)
                .map(dish -> new ResponseEntity<>(userService.findByAuthoredDish(dish), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/users/liked-dish/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<User>> findByLikedDish(@PathVariable Long id){
        return dishService.findById(id)
                .map(dish -> new ResponseEntity<>(userService.findByLikedDish(dish), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/users/comment/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> findByComment(@PathVariable Long id){
        return commentService.findById(id)
                .map(comment -> new ResponseEntity<>(userService.findByComment(comment), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/users/commented-dish/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<User>> findByCommentedDish(@PathVariable Long id){
        return dishService.findById(id)
                .map(dish -> new ResponseEntity<>(userService.findByCommentedDish(dish), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}
