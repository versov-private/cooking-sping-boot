package com.diploma.cooking.controller;

import com.diploma.cooking.model.Product;
import com.diploma.cooking.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return products.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findById(Long id) {
        return productService.findById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return product.getId().intValue() == 0
                ? new ResponseEntity<>(productService.saveOrUpdate(product), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/products")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return product.getId().intValue() > 0
                ? new ResponseEntity<>(productService.saveOrUpdate(product), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> {
                    productService.delete(product);
                    return new ResponseEntity<>(product, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}
