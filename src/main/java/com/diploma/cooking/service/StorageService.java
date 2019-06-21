package com.diploma.cooking.service;

import com.diploma.cooking.model.Product;
import com.diploma.cooking.model.Storage;
import com.diploma.cooking.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StorageService {

    List<Storage> findAll();
    Optional<Storage> findById(Long id);
    Storage saveOrUpdate(Storage storage);
    void delete(Storage storage);
    Storage findByUser(User user);
    List<Storage> findByProduct(Product product);
}
