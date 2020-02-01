package io.craftbase.orderapi.adapters.order.jpa;

import io.craftbase.orderapi.adapters.order.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

}