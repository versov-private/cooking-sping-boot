package com.diploma.cooking.service.impl;

import com.diploma.cooking.model.Comment;
import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.User;
import com.diploma.cooking.repository.CommentRepository;
import com.diploma.cooking.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment saveOrUpdate(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public List<Comment> findByDish(Dish dish) {
        return commentRepository.findByDish(dish);
    }

    @Override
    public List<Comment> findByUser(User user) {
        return commentRepository.findByUser(user);
    }

    @Override
    public Comment findByDishAndUser(Dish dish, User user) {
        return commentRepository.findByDishAndUser(dish, user);
    }
}
