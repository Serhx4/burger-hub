package com.github.serhx4.domain.mapper;

import com.github.serhx4.domain.Ingredient;
import com.github.serhx4.domain.dto.IngredientDto;
import org.springframework.stereotype.Component;

@Component
public class IngredientReadMapper implements Mapper<Ingredient, IngredientDto> {

    @Override
    public IngredientDto map(Ingredient object) {
        return new IngredientDto(
                object.getId(),
                object.getName(),
                object.getPrice(),
                object.getImageUri(),
                object.getType()
        );
    }
}
