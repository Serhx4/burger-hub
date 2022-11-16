package com.github.serhx4.domain.mapper;

import com.github.serhx4.data.UserRepository;
import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.User;
import com.github.serhx4.domain.dto.BurgerCreateDto;
import com.github.serhx4.exception.NoUserFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BurgerCreateMapper implements Mapper<BurgerCreateDto, Burger> {

    private final UserRepository userRepository;

    @Override
    public Burger map(BurgerCreateDto object) {
        return Burger.builder()
                .name(object.getName())
                .imageUri(object.getImageUri())
                .user(getUser(object.getUsername()))
                .ingredients(object.getIngredients())
                .build();
    }

    private User getUser(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new NoUserFoundException(username));
    }
}
