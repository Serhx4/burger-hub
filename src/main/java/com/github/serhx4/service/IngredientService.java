package com.github.serhx4.service;

import com.github.serhx4.domain.Ingredient;

public interface IngredientService {

    Iterable<Ingredient> findAll();

    Iterable<Ingredient> filterByType(Ingredient.Type type);
}
