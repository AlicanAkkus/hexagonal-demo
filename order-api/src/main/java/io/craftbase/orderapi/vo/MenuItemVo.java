package io.craftbase.orderapi.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuItemVo {

    private Long id;
    private Long menuId;
    private String name;
    private String description;
    private BigDecimal price;
}