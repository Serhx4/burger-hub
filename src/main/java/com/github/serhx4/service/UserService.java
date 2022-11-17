package com.github.serhx4.service;

import com.github.serhx4.domain.User;
import com.github.serhx4.security.RegistrationForm;

import java.util.Optional;

public interface UserService {

    Optional<User> save(RegistrationForm form);

}
