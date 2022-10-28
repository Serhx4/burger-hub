package com.github.serhx4.service;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.PromoCode;

import java.math.BigDecimal;
import java.util.Map;

public interface CartService {

    void addBurger(Burger burger);

    void removeBurger(Burger burger);

    void addPromo(PromoCode promoCode);

    PromoCode getPromo();

    Map<Burger, Integer> getBurgersInCart();

    BigDecimal getDiscount();

    BigDecimal getOrderTotal();

    Integer getCount();

    void clearCart();

}
