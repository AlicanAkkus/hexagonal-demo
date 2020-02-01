package io.craftbase.orderapi.adapters.restaurant.jpa;

import io.craftbase.orderapi.adapters.restaurant.jpa.entity.MenuItemEntity;
import io.craftbase.orderapi.restaurant.MenuItemRepository;
import io.craftbase.orderapi.restaurant.model.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemRepositoryJpaAdapter implements MenuItemRepository {

    private final MenuItemJpaRepository menuItemJpaRepository;

    @Override
    public MenuItem retrieve(Long menuItemId) {
        MenuItemEntity menuItemEntity = menuItemJpaRepository.findById(menuItemId).orElseThrow(() -> new RuntimeException("menu item not found"));

        return toModel(menuItemEntity);
    }

    private MenuItem toModel(MenuItemEntity menuItemEntity) {
        return MenuItem.builder()
                .id(menuItemEntity.getId())
                .name(menuItemEntity.getName())
                .menuId(menuItemEntity.getMenuId())
                .price(menuItemEntity.getPrice())
                .description(menuItemEntity.getDescription())
                .build();
    }
}
