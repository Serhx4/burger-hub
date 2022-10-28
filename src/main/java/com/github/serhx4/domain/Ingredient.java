package com.github.serhx4.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final BigDecimal price;
    private final String imageUri;
    private final Type type;

    public enum Type {
        BREAD, PROTEIN, VEGGIES, CHEESE, BACON, SAUCE, TOPPING
    }
}
