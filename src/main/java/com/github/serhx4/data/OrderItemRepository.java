package com.github.serhx4.data;

import com.github.serhx4.domain.Order;
import com.github.serhx4.domain.OrderItem;
import com.github.serhx4.domain.User;
import com.github.serhx4.domain.compositekeys.OrderItemId;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemId> {
}
