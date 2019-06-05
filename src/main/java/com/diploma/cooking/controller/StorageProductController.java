package com.diploma.cooking.controller;

import com.diploma.cooking.model.StorageProduct;
import com.diploma.cooking.service.StorageProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StorageProductController {

    private final StorageProductService storageProductService;

    public StorageProductController(StorageProductService storageProductService) {
        this.storageProductService = storageProductService;
    }

    @GetMapping("/storage-products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<StorageProduct>> findAll() {
        List<StorageProduct> storageProducts = storageProductService.findAll();
        return storageProducts.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(storageProducts, HttpStatus.OK);
    }

    @GetMapping("/storage-products/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<StorageProduct> findById(@PathVariable Long id) {
        return storageProductService.findById(id)
                .map(storageProduct -> new ResponseEntity<>(storageProduct, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/storage-products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<StorageProduct> save(@RequestBody StorageProduct storageProduct) {
        return storageProduct.getId().intValue() == 0
                ? new ResponseEntity<>(storageProductService.saveOrUpdate(storageProduct), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/storage-products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<StorageProduct> update(@RequestBody StorageProduct storageProduct) {
        return storageProduct.getId().intValue() > 0
                ? new ResponseEntity<>(storageProductService.saveOrUpdate(storageProduct), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/storage-products/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<StorageProduct> delete(@PathVariable Long id) {
        return storageProductService.findById(id)
                .map(storageProduct -> {
                    storageProductService.delete(storageProduct);
                    return new ResponseEntity<>(storageProduct, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}
