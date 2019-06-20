package com.diploma.cooking.service;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    List<Ingredient> findAll();
    Optional<Ingredient> findById(Long id);
    Ingredient saveOrUpdate(Ingredient ingredient);
    void delete(Ingredient ingredient);
    List<Ingredient> findByDish(Dish dish);
}
