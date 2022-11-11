package com.github.serhx4.service.implement;

import com.github.serhx4.data.BurgerRepository;
import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.User;
import com.github.serhx4.service.BurgerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BurgerServiceImpl implements BurgerService {

    private final BurgerRepository burgerRepository;

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
    public void deleteById(Long id) {
        burgerRepository.findById(id).ifPresent(this::forget);
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
