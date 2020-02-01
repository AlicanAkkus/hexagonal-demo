package io.craftbase.orderapi.adapters.order.rest.dto;

import io.craftbase.orderapi.order.model.OrderCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    @NotEmpty
    private String note;

    @NotEmpty
    private String address;

    @NotNull
    private BigDecimal price;

    @NotEmpty
    private List<OrderItemDto> orderItems;

    public OrderCreate toModel() {
        return OrderCreate.builder()
                .note(note)
                .price(price)
                .address(address)
                .referenceCode(UUID.randomUUID().toString())
                .orderItems(orderItems.stream().map(OrderItemDto::toModel).collect(Collectors.toList()))
                .build();
    }
}