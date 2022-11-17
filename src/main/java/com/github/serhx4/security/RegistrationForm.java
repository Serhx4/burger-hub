package com.github.serhx4.security;

import com.github.serhx4.domain.User;
import com.github.serhx4.validation.UniqueEmail;
import com.github.serhx4.validation.UniqueUsername;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationForm {

    @NotBlank(message = "Username is required")
    @UniqueUsername
    private String username;

    @Email(message = "Email is not valid")
    @UniqueEmail
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    public User toUser(PasswordEncoder encoder) {
        return new User(username, email, encoder.encode(password), null, null, null);
    }
}
