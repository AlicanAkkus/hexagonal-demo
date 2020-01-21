package io.craftbase.orderapi.order.rest.dto;

import io.craftbase.orderapi.order.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private String name;
    private BigDecimal price;

    public OrderItem toModel() {
        return OrderItem.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }
}