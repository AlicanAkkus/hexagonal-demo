package io.craftbase.restaurantapi.restaurant;

import io.craftbase.restaurantapi.restaurant.model.MenuItem;

public interface MenuItemRepository {

    MenuItem retrieve(Long menuItemId);
}