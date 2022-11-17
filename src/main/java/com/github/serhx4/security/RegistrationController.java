package com.github.serhx4.security;

import com.github.serhx4.exception.NoUserFoundException;
import com.github.serhx4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @ModelAttribute("registrationForm")
    public RegistrationForm form () {
        return new RegistrationForm();
    }

    @GetMapping
    public String registerForm() {
        return "security/registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "security/registration";
        }

        return userService.save(form)
                .map(user -> {
                    redirectAttributes.addFlashAttribute("success", true);
                    redirectAttributes.addFlashAttribute("username", user.getUsername());
                    return "redirect:/login";
                })
                .orElseThrow(() -> new NoUserFoundException(form.getUsername()));
    }
}
