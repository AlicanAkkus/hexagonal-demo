package io.craftbase.orderapi.converter;

import io.craftbase.orderapi.repository.MenuItemEntity;
import io.craftbase.orderapi.vo.MenuItemVo;
import org.springframework.stereotype.Component;

@Component
public class MenuItemEntityToVoConverter {

    public MenuItemVo converToVo(MenuItemEntity menuItemEntity) {
        return MenuItemVo.builder()
                .id(menuItemEntity.getId())
                .name(menuItemEntity.getName())
                .menuId(menuItemEntity.getMenuId())
                .price(menuItemEntity.getPrice())
                .description(menuItemEntity.getDescription())
                .build();
    }
}