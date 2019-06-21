package com.diploma.cooking.service;

import com.diploma.cooking.model.Comment;
import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Storage;
import com.diploma.cooking.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
    User saveOrUpdate(User user);
    void delete(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findByStorage(Storage storage);
    List<User> findByFavouriteDish(Dish dish);
    User findByAuthoredDish(Dish dish);
    List<User> findByLikedDish(Dish dish);
    User findByComment(Comment comment);
    List<User> findByCommentedDish(Dish dish);
}
