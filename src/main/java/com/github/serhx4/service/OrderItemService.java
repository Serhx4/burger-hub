package com.github.serhx4.service;

import com.github.serhx4.domain.Burger;
import com.github.serhx4.domain.OrderItem;
import com.github.serhx4.domain.User;
import com.github.serhx4.domain.compositekeys.OrderItemId;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {

    Optional<OrderItem> findById(OrderItemId id);

    Iterable<OrderItem> findAll();

    OrderItem save(OrderItem orderItem);

    void deleteById(OrderItemId id);

}
