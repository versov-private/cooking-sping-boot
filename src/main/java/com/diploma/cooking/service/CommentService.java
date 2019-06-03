package com.diploma.cooking.service;

import com.diploma.cooking.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> findAll();
    Optional<Comment> findById(Long id);
    Comment saveOrUpdate(Comment comment);
    void delete(Comment comment);
}
