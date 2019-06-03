package com.diploma.cooking.repository;

import com.diploma.cooking.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query("SELECT i.dish FROM Ingredient i WHERE i.product = ?1")
    List<Dish> findDishesByProduct(Product product);

    @Query("SELECT f.dish FROM Favorite f WHERE f.user = ?1")
    List<Dish> findFavouriteDishesByUser(User user);

    @Query("SELECT i.dish FROM Ingredient AS i INNER JOIN i.product AS p INNER JOIN p.storageProducts AS sp WHERE sp.storage = ?1")
    List<Dish> findByStorage(Storage storage);

    @Query("SELECT i.dish FROM Ingredient AS i INNER JOIN i.product AS p INNER JOIN p.storageProducts AS sp INNER JOIN sp.storage AS s WHERE ?1 IN (s.users)")
    List<Dish> findByUser(User user);

    List<Dish> findByTimeOfCooking(float timeOfCooking);

    @Query(value = "SELECT l.dish FROM Like as l WHERE l.user = ?1")
    List<Dish> findByLikeUser(User user);

    @Query(value = "SELECT c.dish FROM Comment as c WHERE c.user = ?1")
    Dish findByComment(Comment comment);

}
