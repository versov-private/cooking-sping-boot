package com.diploma.cooking.controller;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.service.impl.DishServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class DishController {

    private final DishServiceImpl dishService;

    public DishController(DishServiceImpl dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/shared/dishes")
    public ResponseEntity<List<Dish>> findAll(){
        final List<Dish> dishes = dishService.findAll();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/shared/dishes/{id}")
    public ResponseEntity<Dish> findById(@PathVariable Long id){
        return dishService.findById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/dishes")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Dish> save(@RequestBody @Valid Dish dish) {
        if(dish.getId().intValue() == 0 || dish.getId() == null){
            return new ResponseEntity<>(dishService.saveOrUpdate(dish), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/dishes")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Dish> update(@NotNull @RequestBody Dish dish) {
        if(dish.getId() <= 0 || dish.getId() == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dishService.saveOrUpdate(dish), HttpStatus.OK);
    }

    @DeleteMapping("/dishes/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Dish> delete(@PathVariable Long id) {
        final Optional<Dish> dish = dishService.findById(id);
        return dish.map(value ->
        {
            dishService.delete(value);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

}
