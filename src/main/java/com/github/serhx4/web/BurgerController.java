package com.github.serhx4.web;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.Ingredient;
import com.github.serhx4.domain.User;
import com.github.serhx4.service.BurgerService;
import com.github.serhx4.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/burger")
public class BurgerController {

    private BurgerService burgerService;
    private IngredientService ingredientService;

    @Autowired
    public BurgerController(BurgerService burgerService, IngredientService ingredientService) {
        this.burgerService = burgerService;
        this.ingredientService = ingredientService;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Arrays.stream(Ingredient.Type.values())
                .forEach(type ->
                        model.addAttribute(type.toString().toLowerCase(), ingredientService.filterByType(type)));
    }

    @GetMapping("/new_burger")
    public String addNewBurger(Model model) {
        model.addAttribute("burger", new Burger());
        return "burger_form";
    }

    @GetMapping("/update_burger/{id}")
    public String updateBurger(@PathVariable Long id, Model model) {
        model.addAttribute("burger", burgerService.findById(id).orElseGet(Burger::new));
        return "burger_form";
    }

    @GetMapping("/my_burgers")
    public String showUsersBurgers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("burgers", burgerService.findAllByUser(user));
        return "my_burgers";
    }

    @PostMapping("/add_burger")
    public String addBurger(@Valid Burger burger, Errors errors, @AuthenticationPrincipal User user) {

        if(errors.hasErrors()) return "burger_form";

        burgerService.save(burger, user);
        return "redirect:/burger/my_burgers";
    }

    @PostMapping("/delete")
    public String deleteBurger(@RequestParam("burgerId") Long burgerId) {
        burgerService.deleteById(burgerId);
        return "redirect:/burger/my_burgers";
    }
}
