package com.diploma.cooking.service.impl;

import com.diploma.cooking.model.Comment;
import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Storage;
import com.diploma.cooking.model.User;
import com.diploma.cooking.repository.UserRepository;
import com.diploma.cooking.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> findByStorage(Storage storage) {
        return userRepository.findByStorage(storage);
    }

    @Override
    public List<User> findByFavouriteDish(Dish dish) {
        return userRepository.findByFavouriteDish(dish);
    }

    @Override
    public User findByAuthoredDish(Dish dish) {
        return userRepository.findByAuthoredDish(dish);
    }

    @Override
    public List<User> findByLikedDish(Dish dish) {
        return userRepository.findByLikedDish(dish);
    }

    @Override
    public User findByComment(Comment comment) {
        return userRepository.findByComment(comment);
    }

    @Override
    public List<User> findByCommentedDish(Dish dish) {
        return userRepository.findByCommentedDish(dish);
    }
}
