package io.craftbase.orderapi.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    private Long id;
    private Long menuId;
    private String name;
    private String description;
    private BigDecimal price;
}