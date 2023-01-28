package com.github.serhx4.service.implement;

import com.github.serhx4.data.IngredientRepository;
import com.github.serhx4.domain.dto.IngredientDto;
import com.github.serhx4.domain.mapper.IngredientCreateMapper;
import com.github.serhx4.domain.mapper.IngredientReadMapper;
import com.github.serhx4.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientReadMapper ingredientReadMapper;
    private final IngredientCreateMapper ingredientCreateMapper;

    @Override
    public List<IngredientDto> findAll() {
        return ingredientRepository.findAll().stream()
                .map(ingredientReadMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<IngredientDto>> findAllSortedByType() {
        return findAll().stream()
                .collect(Collectors.groupingBy(ingredient -> ingredient.getType().toString().toLowerCase()));
    }

    @Override
    public Optional<IngredientDto> findById(String id) {
        return ingredientRepository.findById(id)
                .map(ingredientReadMapper::map);
    }

    @Override
    public IngredientDto create(IngredientDto ingredientDto) {
        return Optional.of(ingredientDto)
                .map(ingredientCreateMapper::map)
                .map(ingredientRepository::save)
                .map(ingredientReadMapper::map)
                .orElseThrow(Error::new);
    }

    @Override
    public Optional<IngredientDto> update(String id, IngredientDto ingredientDto) {
        return ingredientRepository.findById(id)
                .map(ingredient -> ingredientCreateMapper.map(ingredientDto, ingredient))
                .map(ingredientRepository::saveAndFlush)
                .map(ingredientReadMapper::map);
    }

    @Override
    public boolean delete(String id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> {
                    ingredientRepository.delete(ingredient);
                    ingredientRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
