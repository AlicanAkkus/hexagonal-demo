package io.craftbase.orderapi.order.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Order {

    private Long id;
    private BigDecimal price;
    private LocalDateTime createdDate;
}