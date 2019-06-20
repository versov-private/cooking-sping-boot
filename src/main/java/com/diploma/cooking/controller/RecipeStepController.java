package com.diploma.cooking.controller;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.RecipeStep;
import com.diploma.cooking.service.DishService;
import com.diploma.cooking.service.RecipeStepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RecipeStepController {

    private final RecipeStepService recipeStepService;
    private final DishService dishService;

    public RecipeStepController(RecipeStepService recipeStepService, DishService dishService) {
        this.recipeStepService = recipeStepService;
        this.dishService = dishService;
    }

    @GetMapping("/recipe-steps")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RecipeStep>> findAll() {
        List<RecipeStep> recipeSteps = recipeStepService.findAll();
        return recipeSteps.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(recipeSteps, HttpStatus.OK);
    }

    @GetMapping("/recipe-steps/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<RecipeStep> findById(@PathVariable Long id) {
        return recipeStepService.findById(id)
                .map(recipeStep -> new ResponseEntity<>(recipeStep, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/recipe-steps")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<RecipeStep> save(@RequestBody RecipeStep recipeStep) {
        return recipeStep.getId().intValue() == 0
                ? new ResponseEntity<>(recipeStepService.saveOrUpdate(recipeStep), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/recipe-steps")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<RecipeStep> update(@RequestBody RecipeStep recipeStep) {
        return recipeStep.getId().intValue() > 0
                ? new ResponseEntity<>(recipeStepService.saveOrUpdate(recipeStep), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/recipe-steps/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<RecipeStep> delete(@PathVariable Long id) {
        return recipeStepService.findById(id)
                .map(recipeStep -> {
                    recipeStepService.delete(recipeStep);
                    return new ResponseEntity<>(recipeStep, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/shared/recipe-steps/dish/{id}")
    public ResponseEntity<List<RecipeStep>> findByDish(@PathVariable Long id){
        Dish dish = new Dish();
        try{
            dish = dishService.findById(id).get();
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<RecipeStep> recipeSteps = recipeStepService.findByDishOrderByNumberOfStep(dish);
        if(recipeSteps.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(recipeSteps, HttpStatus.OK);
    }
}
