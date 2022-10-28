package com.github.serhx4.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long burgerId;

    @NotBlank(message = "Please provide a name for your burger")
    private String name;

    private String imageUri = "image/burger/bacon cheeseburger.png";

    private BigDecimal price;

    @PrePersist
    @PreUpdate
    private void price() {
        this.price = ingredients.stream()
                .map(Ingredient::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @ManyToOne
    private User user;

    @NotNull
    @ManyToMany
    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<Ingredient> ingredients;
    public String getDescription() {
        return ingredients
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Burger burger = (Burger) object;

        return burgerId.equals(burger.burgerId);
    }

    @Override
    public int hashCode() {
        return burgerId.hashCode();
    }

}
