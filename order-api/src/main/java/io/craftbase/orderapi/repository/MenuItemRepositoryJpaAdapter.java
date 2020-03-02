package io.craftbase.orderapi.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemRepositoryJpaAdapter {

    private final MenuItemJpaRepository menuItemJpaRepository;

    public MenuItemEntity retrieve(Long menuItemId) {
        return menuItemJpaRepository.findById(menuItemId).orElseThrow(() -> new RuntimeException("menu item not found"));
    }
}