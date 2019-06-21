package com.diploma.cooking.repository;

import com.diploma.cooking.model.Product;
import com.diploma.cooking.model.Storage;
import com.diploma.cooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {

    @Query("select u.storage from User u where u = ?1")
    Storage findByUser(User user);

    @Query("select sp.storage from StorageProduct sp where sp.product = ?1")
    List<Storage> findByProduct(Product product);
}
