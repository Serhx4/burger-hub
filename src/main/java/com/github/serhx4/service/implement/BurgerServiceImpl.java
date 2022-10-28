package com.github.serhx4.service.implement;

import com.github.serhx4.data.BurgerRepository;
import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.User;
import com.github.serhx4.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BurgerServiceImpl implements BurgerService {

    private BurgerRepository burgerRepository;

    @Autowired
    public BurgerServiceImpl(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @Override
    public Optional<Burger> findById(Long id) {
        return burgerRepository.findById(id);
    }

    @Override
    public Iterable<Burger> findAllByUser(User user) {
        return burgerRepository.findAllByUser(user);
    }

    @Override
    public Iterable<Burger> findAllByUsername(String username) {
        return burgerRepository.findAllByUserUsername(username);
    }

    @Override
    public void deleteById(Long burgerId) {
        burgerRepository.findById(burgerId).ifPresent(this::forget);
    }

    @Override
    public void save(Burger burger, User user) {
        burger.setUser(user);
        burgerRepository.save(burger);
    }

    @Override
    public void forget(Burger burger) {
        // forget instead deleting. We can't delete burgers that are currently in cart or order
        burger.setUser(null);
        burgerRepository.save(burger);
    }
}
