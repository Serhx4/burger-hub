package com.github.serhx4.web;

import com.github.serhx4.data.UserRepository;
import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.dto.BurgerCreateDto;
import com.github.serhx4.service.BurgerService;
import com.github.serhx4.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/burger")
@RequiredArgsConstructor
public class BurgerController {

    private final BurgerService burgerService;
    private final UserRepository userRepository;
    private final IngredientService ingredientService;

    @ModelAttribute
    public void addIngredients(Model model) {
        ingredientService.addIngredientsToModel(model);
    }

    @GetMapping("/new")
    public String addNewBurger(@ModelAttribute("burger") BurgerCreateDto burger) {
        return "burger_form";
    }

    @GetMapping("/update/{id}")
    public String updateBurger(@PathVariable Long id, Model model) {
        model.addAttribute("burger", burgerService.findById(id).orElseGet(Burger::new));
        return "burger_form";
    }

    @GetMapping("/my")
    public String showUsersBurgers(Model model,
                                   @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("burgers", burgerService.findAllByUsername(user.getUsername()));
        return "my_burgers";
    }

    @PostMapping("/new")
    public String addBurger(@Valid @ModelAttribute("burger") BurgerCreateDto burger, Errors errors) {
        if(errors.hasErrors()) {
            return "burger_form";
        }

        return "redirect:/menu/" + burgerService.save(burger).getId();
    }

    @PostMapping("/delete")
    public String deleteBurger(@RequestParam("id") Long id) {
        burgerService.deleteById(id);
        return "redirect:/burger/my";
    }
}
