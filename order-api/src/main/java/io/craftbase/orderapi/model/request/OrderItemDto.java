package io.craftbase.orderapi.model.request;

import io.craftbase.orderapi.vo.OrderItemVo;
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

    public OrderItemVo toModel() {
        return OrderItemVo.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }
}