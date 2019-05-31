package com.diploma.cooking.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "recipe_steps",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"number_of_step", "dish_id"})})
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_of_step", nullable = false)
    private int numberOfStep;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @Lob
    private byte[] image;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    public RecipeStep() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfStep() {
        return numberOfStep;
    }

    public void setNumberOfStep(int numberOfStep) {
        this.numberOfStep = numberOfStep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "RecipeStep{" +
                "id=" + id +
                ", numberOfStep=" + numberOfStep +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                ", dish=" + dish +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeStep that = (RecipeStep) o;
        return getId().equals(that.getId()) &&
                getNumberOfStep() == that.getNumberOfStep() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Arrays.equals(getImage(), that.getImage()) &&
                Objects.equals(getDish(), that.getDish());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getNumberOfStep(), getDescription(), getDish());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}
