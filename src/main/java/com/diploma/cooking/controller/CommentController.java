package com.diploma.cooking.controller;

import com.diploma.cooking.model.Comment;
import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.User;
import com.diploma.cooking.service.CommentService;
import com.diploma.cooking.service.DishService;
import com.diploma.cooking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final DishService dishService;
    private final UserService userService;

    public CommentController(CommentService commentService, DishService dishService, UserService userService) {
        this.commentService = commentService;
        this.dishService = dishService;
        this.userService = userService;
    }

    @GetMapping("/comments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Comment>> findAll() {
        List<Comment> comments = commentService.findAll();
        if(comments.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/shared/comments/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        return commentService.findById(id)
                .map(comment -> new ResponseEntity<>(comment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/comments")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Comment> save(@RequestBody Comment comment) {
        if(comment.getId().intValue() == 0){
            return new ResponseEntity<>(commentService.saveOrUpdate(comment), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/comments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Comment> update(@RequestBody Comment comment) {
        if(comment.getId() <= 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(commentService.saveOrUpdate(comment), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Comment> delete(@PathVariable Long id) {
        return commentService.findById(id)
                .map(comment -> {
                    commentService.delete(comment);
                    return new ResponseEntity<>(comment, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/shared/comments/dish/{id}")
    public ResponseEntity<List<Comment>> findByDish(@PathVariable Long id) {
        return dishService.findById(id)
                .map(dish -> new ResponseEntity<>(commentService.findByDish(dish), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/comments/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Comment>> findByUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> new ResponseEntity<>(commentService.findByUser(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/comments/dish/{dishId}/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Comment> findByDishAndUser(@PathVariable("dishId") Long dishId, @PathVariable("userId") Long userId) {
        return dishService.findById(dishId)
                .map(dish -> userService.findById(userId)
                        .map(user -> new ResponseEntity<>(commentService.findByDishAndUser(dish, user), HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST))
                )
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}
