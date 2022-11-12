package com.github.serhx4.service.implement;

import com.github.serhx4.data.UserRepository;
import com.github.serhx4.domain.User;
import com.github.serhx4.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findById(username).isPresent();
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
