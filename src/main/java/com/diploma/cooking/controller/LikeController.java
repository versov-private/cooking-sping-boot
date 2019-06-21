package com.diploma.cooking.controller;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Like;
import com.diploma.cooking.model.RecipeStep;
import com.diploma.cooking.service.DishService;
import com.diploma.cooking.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class LikeController {

    private final LikeService likeService;
    private final DishService dishService;

    public LikeController(LikeService likeService, DishService dishService) {
        this.likeService = likeService;
        this.dishService = dishService;
    }

    @GetMapping("/likes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Like>> findAll() {
        List<Like> likes = likeService.findAll();
        return likes.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping("/likes/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Like> findById(@PathVariable Long id) {
        return likeService.findById(id)
                .map(like -> new ResponseEntity<>(like, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/likes")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Like> save(@RequestBody Like like) {
        return like.getId().intValue() == 0
                ? new ResponseEntity<>(likeService.saveOrUpdate(like), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/likes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Like> update(@RequestBody Like like) {
        return like.getId().intValue() > 0
                ? new ResponseEntity<>(likeService.saveOrUpdate(like), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/likes/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Like> delete(@PathVariable Long id) {
        return likeService.findById(id)
                .map(like -> {
                    likeService.delete(like);
                    return new ResponseEntity<>(like, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/shared/likes/dish/{id}")
    public ResponseEntity<Long> findQuantityByDish(@PathVariable Long id) {
        Dish dish;
        try{
            dish = dishService.findById(id).orElse(null);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Long numberOfLikes = likeService.findQuantityByDish(dish);
        if(numberOfLikes == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(numberOfLikes, HttpStatus.OK);
    }
}
