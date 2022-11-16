package com.github.serhx4.service.implement;

import com.github.serhx4.data.BurgerRepository;
import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.User;
import com.github.serhx4.domain.dto.BurgerCreateDto;
import com.github.serhx4.domain.dto.BurgerReadDto;
import com.github.serhx4.domain.mapper.BurgerCreateMapper;
import com.github.serhx4.domain.mapper.BurgerReadMapper;
import com.github.serhx4.exception.NoItemFoundException;
import com.github.serhx4.service.BurgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BurgerServiceImpl implements BurgerService {

    private final BurgerRepository burgerRepository;
    private final BurgerCreateMapper burgerCreateMapper;
    private final BurgerReadMapper burgerReadMapper;

    @Override
    public Optional<Burger> findById(Long id) {
        return burgerRepository.findById(id);
    }

    @Override
    public List<Burger> findAllById(Iterable<Long> ids) {
        return burgerRepository.findAllById(ids);
    }

    @Override
    public List<Burger> findAllByUsername(String username) {
        return burgerRepository.findAllByUserUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        burgerRepository.findById(id).ifPresent(this::forget);
    }

    @Override
    @Transactional
    public BurgerReadDto save(BurgerCreateDto burgerDto) {
        return Optional.of(burgerDto)
                .map(burgerCreateMapper::map)
                .map(burgerRepository::save)
                .map(burgerReadMapper::map)
                .orElseThrow(() -> new NoItemFoundException("Burger wasn't saved successfully"));
    }

    @Transactional
    private void forget(Burger burger) {
        // forget instead deleting. We can't delete burgers that are currently in cart or order
        burger.setUser(null);
        burgerRepository.saveAndFlush(burger);
    }
}
