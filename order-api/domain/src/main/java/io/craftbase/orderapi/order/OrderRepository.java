package io.craftbase.orderapi.order;

import io.craftbase.orderapi.order.model.Order;
import io.craftbase.orderapi.order.model.OrderCreate;

public interface OrderRepository {

    Order save(OrderCreate orderCreate);
}
