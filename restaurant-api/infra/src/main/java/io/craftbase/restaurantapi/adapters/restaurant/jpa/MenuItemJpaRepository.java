package io.craftbase.restaurantapi.adapters.restaurant.jpa;

import io.craftbase.restaurantapi.adapters.restaurant.jpa.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemJpaRepository extends JpaRepository<MenuItemEntity, Long> {
}