package com.github.serhx4.service.implement;

import com.github.serhx4.data.OrderItemRepository;
import com.github.serhx4.domain.OrderItem;
import com.github.serhx4.domain.compositekeys.OrderItemId;
import com.github.serhx4.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public Optional<OrderItem> findById(OrderItemId id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public Iterable<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteById(OrderItemId id) {
        orderItemRepository.deleteById(id);
    }
}
