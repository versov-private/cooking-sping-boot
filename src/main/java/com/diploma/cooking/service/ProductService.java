package com.diploma.cooking.service;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Product;
import com.diploma.cooking.model.Storage;
import com.diploma.cooking.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product saveOrUpdate(Product product);
    void delete(Product product);
    List<Product> findByDish(Dish dish);
    List<Product> findByStorage(Storage storage);
    List<Product> findByUser(User user);
}
