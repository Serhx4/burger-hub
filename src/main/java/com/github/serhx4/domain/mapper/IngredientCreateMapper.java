package com.github.serhx4.domain.mapper;

import com.github.serhx4.domain.Ingredient;
import com.github.serhx4.domain.dto.IngredientDto;
import org.springframework.stereotype.Component;

@Component
public class IngredientCreateMapper implements Mapper<IngredientDto, Ingredient> {

    @Override
    public Ingredient map(IngredientDto object) {
        Ingredient ingredient = new Ingredient();
        copy(object, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient map(IngredientDto fromObject, Ingredient toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(IngredientDto dto, Ingredient ingredient) {
        ingredient.setId(dto.getId());
        ingredient.setName(dto.getName());
        ingredient.setPrice(dto.getPrice());
        ingredient.setImageUri(dto.getImageUri());
        ingredient.setType(dto.getType());
    }
}
