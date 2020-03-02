package io.craftbase.orderapi.service;

import io.craftbase.orderapi.converter.MenuItemEntityToVoConverter;
import io.craftbase.orderapi.repository.MenuItemEntity;
import io.craftbase.orderapi.repository.MenuItemRepositoryJpaAdapter;
import io.craftbase.orderapi.vo.MenuItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final MenuItemRepositoryJpaAdapter menuItemRepositoryJpaAdapter;
    private final MenuItemEntityToVoConverter menuItemEntityToVoConverter;

    public MenuItemVo retrieveMenuItem(Long menuItemId) {
        MenuItemEntity menuItemEntity = menuItemRepositoryJpaAdapter.retrieve(menuItemId);
        return menuItemEntityToVoConverter.converToVo(menuItemEntity);
    }
}