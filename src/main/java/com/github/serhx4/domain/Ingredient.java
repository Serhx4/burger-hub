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

    public enum Type {
        BREAD, PROTEIN, VEGGIES, CHEESE, BACON, SAUCE, TOPPING
    }
}
