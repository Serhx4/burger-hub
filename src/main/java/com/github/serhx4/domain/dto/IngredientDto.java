package com.github.serhx4.domain.dto;

import com.github.serhx4.domain.Ingredient;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Value
public class IngredientDto {

    @NotBlank
    String id;
    @NotBlank
    String name;
    BigDecimal price;
    String imageUri;
    Ingredient.Type type;
}
