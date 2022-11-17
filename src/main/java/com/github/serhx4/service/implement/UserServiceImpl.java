package com.github.serhx4.service.implement;

import com.github.serhx4.data.UserRepository;
import com.github.serhx4.domain.User;
import com.github.serhx4.security.RegistrationForm;
import com.github.serhx4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> save(RegistrationForm form) {
        return Optional.of(form)
                .map(it -> it.toUser(passwordEncoder))
                .map(userRepository::save);
    }
}
