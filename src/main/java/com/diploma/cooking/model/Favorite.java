package com.diploma.cooking.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favourites",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "dish_id"})})
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    public Favorite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", user=" + user +
                ", dish=" + dish +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return getId().equals(favorite.getId()) &&
                Objects.equals(getUser(), favorite.getUser()) &&
                Objects.equals(getDish(), favorite.getDish());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDish());
    }
}
