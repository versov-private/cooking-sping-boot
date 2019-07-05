package com.diploma.cooking.controller;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Product;
import com.diploma.cooking.model.Storage;
import com.diploma.cooking.model.User;
import com.diploma.cooking.service.DishService;
import com.diploma.cooking.service.ProductService;
import com.diploma.cooking.service.StorageService;
import com.diploma.cooking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {

    private final ProductService productService;
    private final DishService dishService;
    private final StorageService storageService;
    private final UserService userService;

    public ProductController(ProductService productService, DishService dishService, StorageService storageService, UserService userService) {
        this.productService = productService;
        this.dishService = dishService;
        this.storageService = storageService;
        this.userService = userService;
    }

    @GetMapping("/products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return products.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return product.getId().intValue() == 0
                ? new ResponseEntity<>(productService.saveOrUpdate(product), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return product.getId().intValue() > 0
                ? new ResponseEntity<>(productService.saveOrUpdate(product), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        return productService.findById(id)
                .map(product -> {
                    productService.delete(product);
                    return new ResponseEntity<>(product, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/shared/products/dish/{id}")
    public ResponseEntity<List<Product>> findByDish(@PathVariable Long id) {
        Dish dish = new Dish();
        try{
           dish = dishService.findById(id).get();
        } catch (NoSuchElementException e){
            new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Product> products = productService.findByDish(dish);
        if(products.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/storage/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Product>> findByStorage(@PathVariable Long id) {
        Storage storage = new Storage();
        try{
            storage = storageService.findById(id).get();
        } catch (NoSuchElementException e){
            new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Product> products = productService.findByStorage(storage);
        if(products.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/user/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Product>> findByUser(@PathVariable Long id) {
        User user = new User();
        try{
            user = userService.findById(id).get();
        } catch (NoSuchElementException e){
            new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Product> products = productService.findByUser(user);
        if(products.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
