package io.craftbase.orderapi.order;

import io.craftbase.orderapi.order.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderRepository orderRepository;
    private final RestaurantPort restaurantPort;
    private final PaymentPort paymentPort;

    @Transactional
    public Order create(OrderCreate orderCreate) {
        validate(orderCreate);
        pay(orderCreate);

        Order order = save(orderCreate);

        log.info("Order has completed with id: {}", order.getId());
        return order;
    }

    private void validate(OrderCreate orderCreate) {
        BigDecimal orderPrice = orderCreate.getOrderItems().stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (!orderPrice.equals(orderCreate.getPrice())) {
            throw new RuntimeException("order price is not equal to sum of the price");
        }

        orderCreate.getOrderItems().forEach(orderItem -> {
            MenuItem menuItem = restaurantPort.retrieveMenuItem(orderItem.getId());

            if (!menuItem.getPrice().equals(orderItem.getPrice())) {
                throw new RuntimeException("menu item price is not equal to item price");
            }
        });
    }

    private void pay(OrderCreate orderCreate) {
        Payment payment = paymentPort.pay(orderCreate.getCardId(), orderCreate.getPrice(), orderCreate.getReferenceCode());
        log.debug("Payment completed with reference code {} and price {}", payment.getReferenceCode(), payment.getPrice());
    }

    private Order save(OrderCreate orderCreate) {
        return orderRepository.save(orderCreate);
    }
}