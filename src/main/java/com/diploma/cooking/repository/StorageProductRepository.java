package com.diploma.cooking.repository;

import com.diploma.cooking.model.Product;
import com.diploma.cooking.model.Storage;
import com.diploma.cooking.model.StorageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageProductRepository extends JpaRepository<StorageProduct, Long> {

    StorageProduct findByStorageAndProduct(Storage storage, Product product);
}
