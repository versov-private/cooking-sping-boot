package com.diploma.cooking.model;

import com.diploma.cooking.model.addition.DishType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "dishes")
public class Dish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DishType type;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "time_of_cooking", nullable = false)
    private float timeOfCooking;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_create", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOfCreate;

    @Lob
    private byte[] image;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @JsonIgnore
    @OneToMany(mappedBy = "dish",cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;

    @JsonIgnore
    @OneToMany(mappedBy = "dish",cascade = CascadeType.ALL)
    private Set<Favorite> favorites;

    @JsonIgnore
    @OneToMany(mappedBy = "dish",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Like> like;

    @JsonIgnore
    @OneToMany(mappedBy = "dish",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "dish",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecipeStep> recipeSteps;

    public Dish() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTimeOfCooking() {
        return timeOfCooking;
    }

    public void setTimeOfCooking(float timeOfCooking) {
        this.timeOfCooking = timeOfCooking;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Set<Like> getLike() {
        return like;
    }

    public void setLike(Set<Like> like) {
        this.like = like;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<RecipeStep> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(Set<RecipeStep> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", timeOfCooking=" + timeOfCooking +
                ", dateOfCreate=" + dateOfCreate +
                ", authorClient=" + author.getFirstName() + " " + author.getLastName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return getId().equals(dish.getId()) &&
                Float.compare(dish.getTimeOfCooking(), getTimeOfCooking()) == 0 &&
                Objects.equals(getName(), dish.getName()) &&
                getType() == dish.getType() &&
                Objects.equals(getDescription(), dish.getDescription()) &&
                Objects.equals(getDateOfCreate(), dish.getDateOfCreate()) &&
                Arrays.equals(getImage(), dish.getImage());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getName(), getType(), getDescription(), getTimeOfCooking(), getDateOfCreate());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}