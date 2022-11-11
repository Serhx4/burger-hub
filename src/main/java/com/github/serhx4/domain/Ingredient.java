package com.github.serhx4.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String imageUri;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToMany(mappedBy = "ingredients")
    @ToString.Exclude
    private List<Burger> burgers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ingredient that = (Ingredient) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public enum Type {
        BREAD, PROTEIN, VEGGIES, CHEESE, BACON, SAUCE, TOPPING
    }
}
