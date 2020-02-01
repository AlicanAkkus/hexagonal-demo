package io.craftbase.orderapi.order;

import io.craftbase.orderapi.order.model.Order;
import io.craftbase.orderapi.order.model.OrderCreate;
import io.craftbase.orderapi.order.model.OrderItem;
import io.craftbase.orderapi.payment.PaymentFacade;
import io.craftbase.orderapi.payment.model.Payment;
import io.craftbase.orderapi.restaurant.RestaurantFacade;
import io.craftbase.orderapi.restaurant.model.MenuItem;
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
    private final RestaurantFacade restaurantFacade;
    private final PaymentFacade paymentFacade;

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
            throw new RuntimeException("order price not equal to sum of price");
        }

        orderCreate.getOrderItems().forEach(orderItem -> {
            MenuItem menuItem = restaurantFacade.retrieveMenuItem(orderItem.getId());

            if (!menuItem.getPrice().equals(orderItem.getPrice())) {
                throw new RuntimeException("menu item price not equal to item price");
            }
        });
    }

    private void pay(OrderCreate orderCreate) {
        Payment payment = paymentFacade.pay(orderCreate.getPrice(), orderCreate.getReferenceCode());
        log.debug("Pay completed with reference code {} and price {}", payment.getReferenceCode(), payment.getPrice());
    }

    private Order save(OrderCreate orderCreate) {
        return orderRepository.save(orderCreate);
    }
}