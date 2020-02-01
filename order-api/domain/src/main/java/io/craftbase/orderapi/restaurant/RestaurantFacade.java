package io.craftbase.orderapi.restaurant;

import io.craftbase.orderapi.restaurant.model.MenuItem;
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