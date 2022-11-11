package com.github.serhx4.data;

import com.github.serhx4.domain.Order;
import com.github.serhx4.domain.ShippingInfo;
import com.github.serhx4.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findByUserUsername(String username);
}
