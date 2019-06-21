package com.diploma.cooking.repository;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Product;
import com.diploma.cooking.model.Storage;
import com.diploma.cooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select i.product from Ingredient i where i.dish = ?1 ")
    List<Product> findByDish(Dish dish);

    @Query("select sp.product from StorageProduct as sp where sp.storage = ?1")
    List<Product> findByStorage(Storage storage);

    @Query("select sp.product from StorageProduct as sp inner join sp.storage as s where ?1 in (s.users)")
    List<Product> findByUser(User user);
}
