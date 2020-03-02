package io.craftbase.orderapi.validator;

import io.craftbase.orderapi.service.RestaurantService;
import io.craftbase.orderapi.vo.MenuItemVo;
import io.craftbase.orderapi.vo.OrderCreateVo;
import io.craftbase.orderapi.vo.OrderItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OrderCreateValidator {

    private final RestaurantService restaurantService;

    public void validate(OrderCreateVo orderCreateVo) {
        BigDecimal orderPrice = orderCreateVo.getOrderItemVos().stream().map(OrderItemVo::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (!orderPrice.equals(orderCreateVo.getPrice())) {
            throw new RuntimeException("order price not equal to sum of price");
        }

        orderCreateVo.getOrderItemVos().forEach(orderItemVo -> {
            MenuItemVo menuItemVo = restaurantService.retrieveMenuItem(orderItemVo.getId());

            if (!menuItemVo.getPrice().equals(orderItemVo.getPrice())) {
                throw new RuntimeException("menu item price not equal to item price");
            }
        });
    }
}