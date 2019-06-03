package com.diploma.cooking.service.impl;

import com.diploma.cooking.model.Favorite;
import com.diploma.cooking.repository.FavoriteRepository;
import com.diploma.cooking.service.FavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Optional<Favorite> findById(Long id) {
        return favoriteRepository.findById(id);
    }

    @Override
    public Favorite saveOrUpdate(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }
}
