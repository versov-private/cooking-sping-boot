package com.diploma.cooking.repository;

import com.diploma.cooking.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
