package com.diploma.cooking.service.impl;

import com.diploma.cooking.model.StorageProduct;
import com.diploma.cooking.repository.StorageProductRepository;
import com.diploma.cooking.service.StorageProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageProductServiceImpl implements StorageProductService {

    private final StorageProductRepository storageProductRepository;

    public StorageProductServiceImpl(StorageProductRepository storageProductRepository) {
        this.storageProductRepository = storageProductRepository;
    }

    @Override
    public List<StorageProduct> findAll() {
        return storageProductRepository.findAll();
    }

    @Override
    public Optional<StorageProduct> findById(Long id) {
        return storageProductRepository.findById(id);
    }

    @Override
    public StorageProduct saveOrUpdate(StorageProduct storageProduct) {
        return storageProductRepository.save(storageProduct);
    }

    @Override
    public void delete(StorageProduct storageProduct) {
        storageProductRepository.delete(storageProduct);
    }
}
