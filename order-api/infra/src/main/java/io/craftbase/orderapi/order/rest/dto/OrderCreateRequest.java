package io.craftbase.orderapi.order.rest.dto;

import io.craftbase.orderapi.order.model.OrderCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    private BigDecimal price;
    private String address;
    private String note;
    private List<OrderItemDto> orderItems;

    public OrderCreate toModel() {
        return OrderCreate.builder()
                .note(note)
                .price(price)
                .address(address)
                .orderItems(orderItems.stream().map(OrderItemDto::toModel).collect(Collectors.toList()))
                .build();
    }
}