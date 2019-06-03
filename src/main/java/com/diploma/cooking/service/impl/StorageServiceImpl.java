package com.diploma.cooking.service.impl;

import com.diploma.cooking.model.Storage;
import com.diploma.cooking.repository.StorageRepository;
import com.diploma.cooking.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;

    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Override
    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    @Override
    public Optional<Storage> findById(Long id) {
        return storageRepository.findById(id);
    }

    @Override
    public Storage saveOrUpdate(Storage storage) {
        return storageRepository.save(storage);
    }

    @Override
    public void delete(Storage storage) {
        storageRepository.delete(storage);
    }
}
