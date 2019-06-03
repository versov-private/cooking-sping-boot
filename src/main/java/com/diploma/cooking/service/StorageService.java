package com.diploma.cooking.service;

import com.diploma.cooking.model.Storage;

import java.util.List;
import java.util.Optional;

public interface StorageService {

    List<Storage> findAll();
    Optional<Storage> findById(Long id);
    Storage saveOrUpdate(Storage storage);
    void delete(Storage storage);
}
