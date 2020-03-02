package io.craftbase.orderapi.model.request;

import io.craftbase.orderapi.vo.OrderCreateVo;
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

    public OrderCreateVo toModel() {
        return OrderCreateVo.builder()
                .note(note)
                .price(price)
                .address(address)
                .referenceCode(UUID.randomUUID().toString())
                .orderItemVos(orderItems.stream().map(OrderItemDto::toModel).collect(Collectors.toList()))
                .build();
    }
}