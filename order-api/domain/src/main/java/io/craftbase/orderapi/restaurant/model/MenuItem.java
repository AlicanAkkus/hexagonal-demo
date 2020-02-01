package io.craftbase.orderapi.restaurant.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuItem {

    private Long id;
    private Long menuId;
    private String name;
    private String description;
    private BigDecimal price;
}