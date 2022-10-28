package com.github.serhx4.data;

import com.github.serhx4.domain.Order;
import com.github.serhx4.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findAllByShippingInfoUser(User user);
}
