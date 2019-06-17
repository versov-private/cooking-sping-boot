package com.diploma.cooking.repository;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select i.product from Ingredient i where i.dish = ?1 ")
    List<Product> findByDish(Dish dish);
}
