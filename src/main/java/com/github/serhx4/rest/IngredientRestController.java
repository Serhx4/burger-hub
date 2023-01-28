package com.github.serhx4.rest;

import com.github.serhx4.domain.dto.IngredientDto;
import com.github.serhx4.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/ingredients")
public class IngredientRestController {

    private final IngredientService ingredientService;

    @GetMapping
    public List<IngredientDto> findAll() {
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public IngredientDto findById(@PathVariable("id") String id) {
        return ingredientService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientDto create(@Valid @RequestBody IngredientDto ingredientDto) {
        return ingredientService.create(ingredientDto);
    }

    @PutMapping("/{id}")
    public IngredientDto update(@PathVariable("id") String id,
                                @Valid @RequestBody IngredientDto ingredientDto) {
        return ingredientService.update(id, ingredientDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        if (!ingredientService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
