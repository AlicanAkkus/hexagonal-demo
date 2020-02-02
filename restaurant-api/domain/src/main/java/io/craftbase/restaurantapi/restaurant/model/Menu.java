package io.craftbase.restaurantapi.restaurant.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class Menu {

    private Long id;
    private String name;
    private String description;
    private List<MenuItem> menuItems;
}