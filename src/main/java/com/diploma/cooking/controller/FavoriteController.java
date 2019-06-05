package com.diploma.cooking.controller;

import com.diploma.cooking.model.Favorite;
import com.diploma.cooking.service.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/favorites")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Favorite>> findAll() {
        List<Favorite> favorites = favoriteService.findAll();
        if(favorites.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @GetMapping("/favorites/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Favorite> findById(@PathVariable Long id) {
        return favoriteService.findById(id)
                .map(favorite -> new ResponseEntity<>(favorite, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/favorites")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Favorite> save(@RequestBody Favorite favorite) {
        if(favorite.getId().intValue() == 0){
            return new ResponseEntity<>(favoriteService.saveOrUpdate(favorite), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/favorites")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Favorite> update(@RequestBody Favorite favorite) {
        if(favorite.getId().intValue() > 0){
            return new ResponseEntity<>(favoriteService.saveOrUpdate(favorite), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/favorites/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Favorite> delete(@PathVariable Long id) {
        return favoriteService.findById(id)
                .map(favorite -> {
                    favoriteService.delete(favorite);
                    return new ResponseEntity<>(favorite, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }
}
