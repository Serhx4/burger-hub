package com.github.serhx4.web;

import com.github.serhx4.data.BurgerRepository;
import com.github.serhx4.domain.Burger;
import com.github.serhx4.exception.NoItemFoundException;
import com.github.serhx4.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private BurgerService burgerService;

    @Autowired
    public HomeController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @GetMapping
    public String welcomeHome(Model model) {
        Iterable<Burger> burgers = burgerService.findAllByUsername("chef");
        model.addAttribute("burgers", burgers);
        return "home";
    }

    @GetMapping("/menu/{id}")
    public String showBurger(@PathVariable Long id, Model model){
        Burger burger = burgerService.findById(id)
                .orElseThrow(() -> new NoItemFoundException("No Burger exists with id=" + id));
        model.addAttribute("burger", burger);
        return "burger";
    }

}
