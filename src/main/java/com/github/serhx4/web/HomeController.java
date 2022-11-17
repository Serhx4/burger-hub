package com.github.serhx4.web;

import com.github.serhx4.exception.NoItemFoundException;
import com.github.serhx4.service.BurgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final BurgerService burgerService;

    @GetMapping
    public String welcomeHome(Model model) {
        model.addAttribute("burgers", burgerService.findAllByUsername("chef"));
        return "home";
    }

    @GetMapping("/menu/{id}")
    public String showBurger(@PathVariable Long id, Model model){
        return burgerService.findById(id)
                .map(burger ->  {
                    model.addAttribute("burger", burger);
                    return "burger";
                })
                .orElseThrow(() -> new NoItemFoundException(HttpStatus.NOT_FOUND,
                        "No Burger exists with id=" + id));
    }

}
