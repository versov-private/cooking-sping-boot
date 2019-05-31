package com.diploma.cooking.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "favourites",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "dish_id"})})
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    public Favourite() {
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
        return "Favourite{" +
                "id=" + id +
                ", user=" + user +
                ", dish=" + dish +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favourite favourite = (Favourite) o;
        return getId().equals(favourite.getId()) &&
                Objects.equals(getUser(), favourite.getUser()) &&
                Objects.equals(getDish(), favourite.getDish());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDish());
    }
}
