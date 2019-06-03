package com.diploma.cooking.service.impl;

import com.diploma.cooking.model.Ingredient;
import com.diploma.cooking.repository.IngredientRepository;
import com.diploma.cooking.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public Ingredient saveOrUpdate(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }
}
