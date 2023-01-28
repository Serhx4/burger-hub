package com.github.serhx4.service;

import com.github.serhx4.domain.dto.IngredientDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IngredientService {

    List<IngredientDto> findAll();

    Map<String, List<IngredientDto>> findAllSortedByType();

    Optional<IngredientDto> findById(String id);

    IngredientDto create(IngredientDto ingredientDto);

    Optional<IngredientDto> update(String id, IngredientDto ingredientDto);

    boolean delete(String id);

}
