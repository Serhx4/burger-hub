package com.github.serhx4.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
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

    private String imageUri;

    private BigDecimal price;

    @ManyToOne
    private User user;

    @ManyToMany
    @Size(min = 1, message = "You must choose at least one ingredient")
    @JoinTable
    @Builder.Default
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
}
