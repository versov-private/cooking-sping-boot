package com.diploma.cooking.repository;

import com.diploma.cooking.model.StorageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageProductRepository extends JpaRepository<StorageProduct, Long> {
}
