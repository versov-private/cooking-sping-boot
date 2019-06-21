package com.diploma.cooking.repository;

import com.diploma.cooking.model.Comment;
import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByDish(Dish dish);
    List<Comment> findByUser(User user);
    Comment findByDishAndUser(Dish dish, User user);
}
