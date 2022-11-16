package com.github.serhx4.web;

import com.github.serhx4.service.BurgerService;
import com.github.serhx4.service.CartService;
import com.github.serhx4.service.PromoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final BurgerService burgerService;
    private final PromoService promoService;

    @ModelAttribute("count")
    public Integer count() {
        return cartService.getCount();
    }

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("burgers", cartService.getBurgersInCart());
        model.addAttribute("total", cartService.getTotal());
        model.addAttribute("promo", cartService.getPromo());
        model.addAttribute("discount", cartService.getDiscount());
        return "cart";
    }

    @PostMapping
    public String addToCart(@RequestParam("id") Long id) {
        burgerService.findById(id).ifPresent(cartService::addBurger);
        return "redirect:/cart";
    }

    @PostMapping("/increment")
    public String incrementQuantity(@RequestParam("id") Long id) {
        burgerService.findById(id).ifPresent(cartService::addBurger);
        return "redirect:/cart";
    }

    @PostMapping("/decrement")
    public String decrementQuantity(@RequestParam("id") Long id) {
        burgerService.findById(id).ifPresent(cartService::removeBurger);
        return "redirect:/cart";
    }

    @PostMapping("/promo")
    public String redeemPromo(@RequestParam("promoCode") String promoCode) {
        promoService.findByCode(promoCode).ifPresent(cartService::addPromo);
        return "redirect:/cart";
    }
}
