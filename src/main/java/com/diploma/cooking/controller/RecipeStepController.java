package com.diploma.cooking.controller;

import com.diploma.cooking.model.RecipeStep;
import com.diploma.cooking.service.RecipeStepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeStepController {

    private final RecipeStepService recipeStepService;

    public RecipeStepController(RecipeStepService recipeStepService) {
        this.recipeStepService = recipeStepService;
    }

    @GetMapping("/recipe-steps")
    public ResponseEntity<List<RecipeStep>> findAll() {
        List<RecipeStep> recipeSteps = recipeStepService.findAll();
        return recipeSteps.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(recipeSteps, HttpStatus.OK);
    }

    @GetMapping("/recipe-steps/{id}")
    public ResponseEntity<RecipeStep> findById(@PathVariable Long id) {
        return recipeStepService.findById(id)
                .map(recipeStep -> new ResponseEntity<>(recipeStep, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/recipe-steps")
    public ResponseEntity<RecipeStep> save(@RequestBody RecipeStep recipeStep) {
        return recipeStep.getId().intValue() == 0
                ? new ResponseEntity<>(recipeStepService.saveOrUpdate(recipeStep), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/recipe-steps")
    public ResponseEntity<RecipeStep> update(@RequestBody RecipeStep recipeStep) {
        return recipeStep.getId().intValue() > 0
                ? new ResponseEntity<>(recipeStepService.saveOrUpdate(recipeStep), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/recipe-steps/{id}")
    public ResponseEntity<RecipeStep> delete(@PathVariable Long id) {
        return recipeStepService.findById(id)
                .map(recipeStep -> {
                    recipeStepService.delete(recipeStep);
                    return new ResponseEntity<>(recipeStep, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}
