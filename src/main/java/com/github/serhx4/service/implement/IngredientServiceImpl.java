package com.github.serhx4.service.implement;

import com.github.serhx4.data.IngredientRepository;
import com.github.serhx4.domain.Ingredient;
import com.github.serhx4.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Iterable<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public void addIngredientsToModel(Model model) {
        Arrays.stream(Ingredient.Type.values())
                .forEach(type ->
                        model.addAttribute(type.toString().toLowerCase(), filterByType(type)));
    }

    private Iterable<Ingredient> filterByType(Ingredient.Type type) {
        return StreamSupport
                .stream(findAll().spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
