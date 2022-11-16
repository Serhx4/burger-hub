package com.github.serhx4.domain.dto;

import com.github.serhx4.domain.Ingredient;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Value
public class BurgerReadDto {

    Long id;

    @NotBlank(message = "Please provide a name for your burger")
    String name;

    String imageUri = "image/burger/bacon cheeseburger.png";

    String username;

    @Size(min = 1, message = "You must choose at least one ingredient")
    List<Ingredient> ingredients;

    BigDecimal price;

    String description;

}
