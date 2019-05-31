package com.diploma.cooking.controller;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
