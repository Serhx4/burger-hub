package com.github.serhx4.service;

import com.github.serhx4.domain.User;

import java.util.Optional;

public interface UserService {

    boolean usernameExists(String username);

    boolean emailExists(String email);

    void save(User user);

}
