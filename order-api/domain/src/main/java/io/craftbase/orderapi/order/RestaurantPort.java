package io.craftbase.orderapi.order;

import io.craftbase.orderapi.order.model.MenuItem;

public interface RestaurantPort {

    MenuItem retrieveMenuItem(Long id);
}