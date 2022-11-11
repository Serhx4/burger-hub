package com.github.serhx4.security;

import com.github.serhx4.domain.User;
import com.github.serhx4.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("registrationForm")
    public RegistrationForm form () {
        return new RegistrationForm();
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm form, BindingResult result) {

        if (userService.usernameExists(form.getUsername())) {
            result.addError(
                    new FieldError("registrationForm", "username",
                            "Username '" + form.getUsername() + "' is already exists"));
        }

        if (userService.emailExists(form.getEmail())) {
            result.addError(
                    new FieldError("registrationForm", "email",
                            "Email '" + form.getEmail() + "' is already exists"));
        }

        if (result.hasErrors()) {
            return "registration";
        }

        User user = form.toUser(passwordEncoder);
        userService.save(user);

        return "redirect:/login";
    }
}
