package com.github.serhx4.web;

import com.github.serhx4.data.OrderRepository;
import com.github.serhx4.data.ShippingInfoRepository;
import com.github.serhx4.domain.*;
import com.github.serhx4.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/order")
//@SessionAttributes("count")
public class OrderController {

    private CartService cartService;
    private ShippingInfoRepository shippingInfoRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(CartService cartService, ShippingInfoRepository shippingInfoRepository, OrderRepository orderRepository) {
        this.cartService = cartService;
        this.shippingInfoRepository = shippingInfoRepository;
        this.orderRepository = orderRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {return new Order();}

    @ModelAttribute(name = "shippingInfo")
    public ShippingInfo shippingInfo(@AuthenticationPrincipal User user) {
        return shippingInfoRepository.findByUser(user).orElse(new ShippingInfo());
    }

    @ModelAttribute(name = "burgers")
    public Map<Burger, Integer> burgers() {
        return cartService.getBurgersInCart();
    }

    @ModelAttribute(name = "total")
    public BigDecimal total() {
        return cartService.getOrderTotal();
    }

    @ModelAttribute(name = "count")
    public Integer count() {
        return cartService.getCount();
    }

    @ModelAttribute(name = "promo")
    public PromoCode promo() {
        return cartService.getPromo();
    }

    @ModelAttribute(name = "discount")
    public BigDecimal discount() {
        return cartService.getDiscount();
    }

    @GetMapping
    public String getOrderForm() {
        return "checkout";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors orderErrors,
                               @Valid ShippingInfo shippingInfo, Errors shippingErrors,
                               @AuthenticationPrincipal User user) {

        if (orderErrors.hasErrors() || shippingErrors.hasErrors()) {
            return "checkout";
        }

        if(shippingInfo.getUser() == null) shippingInfo.setUser(user);

        order.setShippingInfo(shippingInfo);
        order.setBurgers(cartService.getBurgersInCart());
        order.setPromoCode(cartService.getPromo());
        order.setTotal(cartService.getOrderTotal());

        orderRepository.save(order);

        cartService.clearCart();

        return "redirect:/order/my_orders";
    }

    @GetMapping("/my_orders")
    public String showOrders(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("orders", orderRepository.findAllByShippingInfoUser(user));
        return "my_orders";
    }
}
