package com.diploma.cooking.repository;

import com.diploma.cooking.model.Comment;
import com.diploma.cooking.model.Dish;
import com.diploma.cooking.model.Storage;
import com.diploma.cooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findByStorage(Storage storage);

    @Query("select f.user from Favorite as f where f.dish = ?1")
    List<User> findByFavouriteDish(Dish dish);

    @Query("select d.author from Dish as d where d = ?1")
    User findByAuthoredDish(Dish dish);

    @Query("select l.user from Like as l where l.dish = ?1")
    List<User> findByLikedDish(Dish dish);

    @Query("select c.user from Comment as c where c = ?1")
    User findByComment(Comment comment);

    @Query("select c.user from Comment as c where c.dish = ?1")
    List<User> findByCommentedDish(Dish dish);
}
