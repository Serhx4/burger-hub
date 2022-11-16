package com.github.serhx4.service;

import com.github.serhx4.domain.Ingredient;
import org.springframework.ui.Model;

public interface IngredientService {

    Iterable<Ingredient> findAll();

    void addIngredientsToModel(Model model);
}
