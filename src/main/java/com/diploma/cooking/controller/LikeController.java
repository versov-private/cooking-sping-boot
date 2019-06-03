package com.diploma.cooking.controller;

import com.diploma.cooking.model.Like;
import com.diploma.cooking.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/likes")
    public ResponseEntity<List<Like>> findAll() {
        List<Like> likes = likeService.findAll();
        return likes.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping("/likes/{id}")
    public ResponseEntity<Like> findById(@PathVariable Long id) {
        return likeService.findById(id)
                .map(like -> new ResponseEntity<>(like, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/likes")
    public ResponseEntity<Like> save(@RequestBody Like like) {
        return like.getId().intValue() == 0
                ? new ResponseEntity<>(likeService.saveOrUpdate(like), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/likes")
    public ResponseEntity<Like> update(@RequestBody Like like) {
        return like.getId().intValue() > 0
                ? new ResponseEntity<>(likeService.saveOrUpdate(like), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/likes/{id}")
    public ResponseEntity<Like> delete(@PathVariable Long id) {
        return likeService.findById(id)
                .map(like -> {
                    likeService.delete(like);
                    return new ResponseEntity<>(like, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}
