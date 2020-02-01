package io.craftbase.orderapi.adapters.order.rest.dto;

import io.craftbase.orderapi.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private BigDecimal price;
    private LocalDateTime createdDate;

    public static OrderResponse fromModel(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .price(order.getPrice())
                .createdDate(order.getCreatedDate())
                .build();
    }
}