package com.github.serhx4.service;

import com.github.serhx4.domain.OrderItem;

import java.util.Optional;

public interface OrderItemService {

    Optional<OrderItem> findById(Long id);

    Iterable<OrderItem> findAll();

    OrderItem save(OrderItem orderItem);

    void deleteById(Long id);

}
