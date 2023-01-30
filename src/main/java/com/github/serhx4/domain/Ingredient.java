package com.github.serhx4.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
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
