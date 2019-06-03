package com.diploma.cooking.controller;

import com.diploma.cooking.model.Storage;
import com.diploma.cooking.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/storages")
    public ResponseEntity<List<Storage>> findAll() {
        List<Storage> storages = storageService.findAll();
        return storages.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(storages, HttpStatus.OK);
    }

    @GetMapping("/storages/{id}")
    public ResponseEntity<Storage> findById(@PathVariable Long id) {
        return storageService.findById(id)
                .map(storage -> new ResponseEntity<>(storage, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/storages")
    public ResponseEntity<Storage> save(@RequestBody Storage storage) {
        return storage.getId().intValue() == 0
                ? new ResponseEntity<>(storageService.saveOrUpdate(storage), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/storages")
    public ResponseEntity<Storage> update(@RequestBody Storage storage) {
        return storage.getId().intValue() > 0
                ? new ResponseEntity<>(storageService.saveOrUpdate(storage), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/storages/{id}")
    public ResponseEntity<Storage> delete(@PathVariable Long id) {
        return storageService.findById(id)
                .map(storage -> {
                    storageService.delete(storage);
                    return new ResponseEntity<>(storage, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}
