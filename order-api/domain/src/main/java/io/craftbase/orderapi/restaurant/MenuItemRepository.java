package io.craftbase.orderapi.restaurant;

import io.craftbase.orderapi.restaurant.model.MenuItem;

public interface MenuItemRepository {

    MenuItem retrieve(Long menuItemId);
}