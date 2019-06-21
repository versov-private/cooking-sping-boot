package com.diploma.cooking.service;

import com.diploma.cooking.model.Comment;
import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.User;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> findAll();
    Optional<Comment> findById(Long id);
    Comment saveOrUpdate(Comment comment);
    void delete(Comment comment);
    List<Comment> findByDish(Dish dish);
    List<Comment> findByUser(User user);
    Comment findByDishAndUser(Dish dish, User user);
}
