package com.diploma.cooking.service;

import com.diploma.cooking.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product saveOrUpdate(Product product);
    void delete(Product product);
}
