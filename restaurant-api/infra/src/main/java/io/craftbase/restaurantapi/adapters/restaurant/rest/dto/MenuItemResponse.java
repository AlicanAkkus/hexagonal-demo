package io.craftbase.restaurantapi.adapters.restaurant.rest.dto;

import io.craftbase.restaurantapi.restaurant.model.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {

    private Long id;
    private Long menuId;
    private String name;
    private String description;
    private BigDecimal price;


    public static MenuItemResponse fromModel(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .menuId(menuItem.getMenuId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .build();
    }
}