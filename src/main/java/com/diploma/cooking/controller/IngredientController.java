package com.diploma.cooking.controller;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Ingredient;
import com.diploma.cooking.model.Product;
import com.diploma.cooking.repository.IngredientRepository;
import com.diploma.cooking.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final DishService dishService;

    public IngredientController(IngredientRepository ingredientRepository, DishService dishService) {
        this.ingredientRepository = ingredientRepository;
        this.dishService = dishService;
    }

    @GetMapping("/ingredients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Ingredient>> findAll() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("/ingredients/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Ingredient> findById(@PathVariable Long id){
        return ingredientRepository.findById(id)
                .map(ingredient -> new ResponseEntity<>(ingredient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/ingredients")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Ingredient> save(@RequestBody Ingredient ingredient) {
        if(ingredient.getId().intValue() == 0){
            return new ResponseEntity<>(ingredientRepository.save(ingredient), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/ingredients")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Ingredient> update(@RequestBody Ingredient ingredient) {
        if(ingredient.getId().intValue() > 0){
            return new ResponseEntity<>(ingredientRepository.save(ingredient), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/ingredients/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Ingredient> delete(@PathVariable Long id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> {
                    ingredientRepository.delete(ingredient);
                    return new ResponseEntity<>(ingredient, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/shared/ingredients/dish/{id}")
    public ResponseEntity<List<Ingredient>> findByDish(@PathVariable Long id) {
        Dish dish = new Dish();
        try{
           dish = dishService.findById(id).get();
        } catch (NoSuchElementException e){
            new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Ingredient> ingredients = ingredientRepository.findByDish(dish);
        if(ingredients.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

}
