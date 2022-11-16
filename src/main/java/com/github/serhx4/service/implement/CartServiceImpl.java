package com.github.serhx4.service.implement;

import com.github.serhx4.domain.PromoCode;
import com.github.serhx4.domain.dto.BurgerReadDto;
import com.github.serhx4.service.BurgerService;
import com.github.serhx4.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

    private final BurgerService burgerService;

    private Map<Long, Integer> burgerCart = new HashMap<>();
    private PromoCode promoCode;
    private BigDecimal total = BigDecimal.valueOf(0);


    @Override
    public void addBurger(BurgerReadDto burger) {
        burgerCart.merge(burger.getId(), 1, Integer::sum);
        total = total.add(burger.getPrice());
    }

    @Override
    public void removeBurger(BurgerReadDto burger) {
        Long burgerId = burger.getId();
        if(burgerCart.containsKey(burgerId)) {
            int burgerCount = burgerCart.get(burgerId);
            if(burgerCount > 1) {
                burgerCart.put(burgerId, --burgerCount);
            } else {
                burgerCart.remove(burgerId);
            }
            total = total.subtract(burger.getPrice());
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
    public Map<BurgerReadDto, Integer> getBurgersInCart() {
        List<BurgerReadDto> burgers = burgerService.findAllById(burgerCart.keySet());
        return burgers.stream().collect(Collectors.toMap(x -> x, y -> burgerCart.get(y.getId())));
    }

    @Override
    public BigDecimal getDiscount() {
        if (promoCode == null) return BigDecimal.ZERO;

        return total.multiply(promoCode.getDiscount()).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getTotal() {
        return total.subtract(getDiscount());
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
        promoCode = null;
        total = BigDecimal.valueOf(0);
    }
}
