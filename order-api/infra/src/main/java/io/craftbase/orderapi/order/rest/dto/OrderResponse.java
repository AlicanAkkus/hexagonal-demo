package io.craftbase.orderapi.order.rest.dto;

import io.craftbase.orderapi.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private String note;
    private String address;
    private BigDecimal price;
    private List<Long> orderItemIds;
    private LocalDateTime createdDate;

    public static OrderResponse fromModel(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .note(order.getNote())
                .address(order.getAddress())
                .price(order.getPrice())
                .orderItemIds(order.getOrderItemIds())
                .createdDate(order.getCreatedDate())
                .build();
    }
}