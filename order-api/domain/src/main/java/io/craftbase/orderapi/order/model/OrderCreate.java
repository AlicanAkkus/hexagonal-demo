package io.craftbase.orderapi.order.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderCreate {

    private BigDecimal price;
    private String address;
    private String note;
    private List<OrderItem> orderItems;
}