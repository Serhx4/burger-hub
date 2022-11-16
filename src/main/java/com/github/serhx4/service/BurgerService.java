package com.github.serhx4.service;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.User;
import com.github.serhx4.domain.dto.BurgerCreateDto;
import com.github.serhx4.domain.dto.BurgerReadDto;

import java.util.List;
import java.util.Optional;

public interface BurgerService {

    Optional<Burger> findById(Long id);

    List<Burger> findAllById(Iterable<Long> ids);

    List<Burger> findAllByUsername(String username);

    BurgerReadDto save(BurgerCreateDto burgerDto);

    void deleteById(Long id);
}
