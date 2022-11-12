package com.github.serhx4.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "burgers")
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide a name for your burger")
    private String name;

    private String imageUri = "image/burger/bacon cheeseburger.png";

    private BigDecimal price;

    @ManyToOne
    private User user;

    @ManyToMany
    @Size(min = 1, message = "You must choose at least one ingredient")
    @JoinTable
    @ToString.Exclude
    private List<Ingredient> ingredients = new ArrayList<>();

    @PrePersist
    @PreUpdate
    private void price() {
        this.price = ingredients.stream()
                .map(Ingredient::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

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
        return id != null && Objects.equals(id, burger.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
