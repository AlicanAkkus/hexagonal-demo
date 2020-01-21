package io.craftbase.orderapi.order;

import io.craftbase.orderapi.order.model.Order;
import io.craftbase.orderapi.order.model.OrderCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderRepository orderRepository;

    public Order create(OrderCreate orderCreate) {
        return Order.builder().id(1L).build();
    }
}