package com.github.serhx4.domain.mapper;

import com.github.serhx4.domain.Ingredient;
import com.github.serhx4.domain.dto.IngredientForm;
import org.springframework.stereotype.Component;

@Component
public class IngredientFormMapper implements Mapper<IngredientForm, Ingredient> {

    @Override
    public Ingredient map(IngredientForm object) {
        Ingredient ingredient = new Ingredient();
        copy(object, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient map(IngredientForm fromObject, Ingredient toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(IngredientForm form, Ingredient ingredient) {
        ingredient.setId(form.getId());
        ingredient.setName(form.getName());
        ingredient.setPrice(form.getPrice());
        ingredient.setImageUri(form.getImageUri());
        ingredient.setType(form.getType());
    }
}
