package com.github.serhx4.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long burgerId;

    @NotBlank(message = "Please provide a name for your burger")
    private String name;

    private String imageUri = "image/burger/bacon cheeseburger.png";

    private BigDecimal price;

    @ManyToOne
    private User user;

    @PrePersist
    @PreUpdate
    private void price() {
        this.price = ingredients.stream()
                .map(Ingredient::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @NotNull
    @ManyToMany
    @Size(min = 1, message = "You must choose at least one ingredient")
    @ToString.Exclude
    private List<Ingredient> ingredients;

    public String getDescription() {
        return ingredients
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Burger burger = (Burger) o;
        return burgerId != null && Objects.equals(burgerId, burger.burgerId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
