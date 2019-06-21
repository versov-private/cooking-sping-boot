package com.diploma.cooking.service.impl;

import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Like;
import com.diploma.cooking.model.User;
import com.diploma.cooking.repository.LikeRepository;
import com.diploma.cooking.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public Optional<Like> findById(Long id) {
        return likeRepository.findById(id);
    }

    @Override
    public Like saveOrUpdate(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public void delete(Like like) {
        likeRepository.delete(like);
    }

    @Override
    public Long findQuantityByDish(Dish dish) {
        return likeRepository.findQuantityByDish(dish);
    }

    @Override
    public Like findByDishAndUser(Dish dish, User user) {
        return likeRepository.findByDishAndUser(dish, user);
    }

    @Override
    public Boolean existsByDishAndUser(Dish dish, User user) {
        return likeRepository.existsByDishAndUser(dish, user);
    }
}
