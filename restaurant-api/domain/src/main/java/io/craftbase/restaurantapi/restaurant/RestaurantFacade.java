package io.craftbase.restaurantapi.restaurant;

import io.craftbase.restaurantapi.restaurant.model.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantFacade {

    private final MenuItemRepository menuItemRepository;

    public MenuItem retrieveMenuItem(Long menuItemId) {
        return menuItemRepository.retrieve(menuItemId);
    }
}