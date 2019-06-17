package com.diploma.cooking.service.impl;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.RecipeStep;
import com.diploma.cooking.repository.RecipeStepRepository;
import com.diploma.cooking.service.RecipeStepService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeStepServiceImpl implements RecipeStepService {

    private final RecipeStepRepository recipeStepRepository;

    public RecipeStepServiceImpl(RecipeStepRepository recipeStepRepository) {
        this.recipeStepRepository = recipeStepRepository;
    }

    @Override
    public List<RecipeStep> findAll() {
        return recipeStepRepository.findAll();
    }

    @Override
    public Optional<RecipeStep> findById(Long id) {
        return recipeStepRepository.findById(id);
    }

    @Override
    public RecipeStep saveOrUpdate(RecipeStep recipeStep) {
        return recipeStepRepository.save(recipeStep);
    }

    @Override
    public void delete(RecipeStep recipeStep) {
        recipeStepRepository.delete(recipeStep);
    }

    @Override
    public List<RecipeStep> findByDish(Dish dish) {
        return recipeStepRepository.findByDish(dish);
    }
}
