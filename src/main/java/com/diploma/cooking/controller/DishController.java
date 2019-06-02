package com.diploma.cooking.controller;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> findAll(){
        final List<Dish> dishes = dishService.findAll();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> findById(@PathVariable Long id){
        final Optional<Dish> dish = dishService.findById(id);
        return dish.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/dishes")
    public ResponseEntity<Dish> save(@RequestBody Dish dish) {
        if(dish.getId().intValue() == 0 || dish.getId() == null){
            dishService.saveOrUpdate(dish);
            return new ResponseEntity<>(dish, HttpStatus.OK);
        }
        return new ResponseEntity<>(dish, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/dishes")
    public ResponseEntity<Dish> update(@RequestBody Dish dish) {
        if(dish.getId() <= 0 || dish.getId() == null){
            return new ResponseEntity<>(dish, HttpStatus.BAD_REQUEST);
        }
        dishService.saveOrUpdate(dish);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<Dish> delete(@PathVariable Long id) {
        final Optional<Dish> dish = dishService.findById(id);
        return dish.map(value ->
        {
            dishService.delete(value);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }






}
