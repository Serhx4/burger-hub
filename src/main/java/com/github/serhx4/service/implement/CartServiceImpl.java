package com.github.serhx4.service.implement;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.PromoCode;
import com.github.serhx4.service.CartService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

    private final Map<Burger, Integer> burgerCart = new HashMap<>();

    private PromoCode promoCode;

    @Override
    public void addBurger(Burger burger) {
        if(burgerCart.containsKey(burger)){
            burgerCart.replace(burger, burgerCart.get(burger) + 1);
        } else {
            burgerCart.put(burger, 1);
        }
    }

    @Override
    public void removeBurger(Burger burger) {
        if(burgerCart.containsKey(burger)) {
            if(burgerCart.get(burger) > 1) {
                burgerCart.replace(burger, burgerCart.get(burger) -1);
            } else {
                burgerCart.remove(burger);
            }
        }
    }

    @Override
    public void addPromo(PromoCode promoCode) {
        this.promoCode = promoCode;
    }

    @Override
    public PromoCode getPromo() {
        return promoCode;
    }

    @Override
    public Map<Burger, Integer> getBurgersInCart() {
        return burgerCart;
    }

    @Override
    public BigDecimal getDiscount() {
        if (promoCode == null) return BigDecimal.ZERO;

        return calculateCartTotal().multiply(promoCode.getDiscount()).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getOrderTotal() {
        return calculateCartTotal().subtract(getDiscount());
    }

    @Override
    public Integer getCount() {
        return burgerCart.values().stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public void clearCart() {
        burgerCart.clear();
        this.promoCode = null;
    }

    private BigDecimal calculateCartTotal() {
        return burgerCart.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
