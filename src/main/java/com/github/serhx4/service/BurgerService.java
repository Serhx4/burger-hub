package com.github.serhx4.service;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.User;

import java.util.List;
import java.util.Optional;

public interface BurgerService {

    Optional<Burger> findById(Long id);

    List<Burger> findAllById(Iterable<Long> ids);

    List<Burger> findAllByUsername(String username);

    void save(Burger burger, User user);

    void forget(Burger burger);

    void deleteById(Long id);
}
