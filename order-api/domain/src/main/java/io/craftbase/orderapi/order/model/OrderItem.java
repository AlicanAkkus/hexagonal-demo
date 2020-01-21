package io.craftbase.orderapi.order.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItem {

    private Long id;
    private String name;
    private BigDecimal price;
}