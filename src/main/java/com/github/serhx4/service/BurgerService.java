package com.github.serhx4.service;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.User;

import java.util.Optional;

public interface BurgerService {

    Optional<Burger> findById(Long id);

    Iterable<Burger> findAllByUser(User user);

    Iterable<Burger> findAllByUsername(String username);

    void save(Burger burger, User user);

    void forget(Burger burger);

    void deleteById(Long burgerId);
}
