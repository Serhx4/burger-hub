package com.github.serhx4.service;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.User;
import com.github.serhx4.domain.dto.BurgerCreateDto;
import com.github.serhx4.domain.dto.BurgerReadDto;

import java.util.List;
import java.util.Optional;

public interface BurgerService {

    Optional<BurgerReadDto> findById(Long id);

    List<BurgerReadDto> findAllById(Iterable<Long> ids);

    List<BurgerReadDto> findAllByUsername(String username);

    BurgerReadDto save(BurgerCreateDto burgerDto);

    void deleteById(Long id);
}
