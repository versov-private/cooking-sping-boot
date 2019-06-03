package com.diploma.cooking.service;

import com.diploma.cooking.model.StorageProduct;

import java.util.List;
import java.util.Optional;

public interface StorageProductService {

    List<StorageProduct> findAll();
    Optional<StorageProduct> findById(Long id);
    StorageProduct saveOrUpdate(StorageProduct storageProduct);
    void delete(StorageProduct storageProduct);
}
