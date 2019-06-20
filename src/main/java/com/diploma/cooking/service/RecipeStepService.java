package com.diploma.cooking.service;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.RecipeStep;

import java.util.List;
import java.util.Optional;

public interface RecipeStepService {

    List<RecipeStep> findAll();
    Optional<RecipeStep> findById(Long id);
    RecipeStep saveOrUpdate(RecipeStep recipeStep);
    void delete(RecipeStep recipeStep);
    List<RecipeStep> findByDishOrderByNumberOfStep(Dish dish);
}
