package io.craftbase.orderapi.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemVo {

    private Long id;
    private String name;
    private BigDecimal price;
}