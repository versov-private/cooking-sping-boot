package com.diploma.cooking.repository;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Ingredient;
import com.diploma.cooking.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByDish(Dish dish);
    Ingredient findByDishAndProduct(Dish dish, Product product);
}
