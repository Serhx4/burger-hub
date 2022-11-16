package com.github.serhx4.service;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.PromoCode;
import com.github.serhx4.domain.dto.BurgerReadDto;

import java.math.BigDecimal;
import java.util.Map;

public interface CartService {

    void addBurger(BurgerReadDto burger);

    void removeBurger(BurgerReadDto burger);

    void addPromo(PromoCode promoCode);

    PromoCode getPromo();

    Map<BurgerReadDto, Integer> getBurgersInCart();

    BigDecimal getDiscount();

    BigDecimal getTotal();

    Integer getCount();

    void clearCart();

}
