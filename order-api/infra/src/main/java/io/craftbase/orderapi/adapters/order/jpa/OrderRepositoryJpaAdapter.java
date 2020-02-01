package io.craftbase.orderapi.adapters.order.jpa;

import io.craftbase.orderapi.adapters.order.jpa.entity.OrderEntity;
import io.craftbase.orderapi.adapters.order.jpa.entity.OrderItemEntity;
import io.craftbase.orderapi.order.OrderRepository;
import io.craftbase.orderapi.order.model.Order;
import io.craftbase.orderapi.order.model.OrderCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderRepositoryJpaAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(OrderCreate orderCreate) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setAddress(orderCreate.getAddress());
        orderEntity.setNote(orderCreate.getNote());
        orderEntity.setPrice(orderCreate.getPrice());
        orderEntity.setReferenceCode(orderCreate.getReferenceCode());

        List<OrderItemEntity> orderItemEntities = createOrderItemEntities(orderEntity, orderCreate);
        orderEntity.setOrderItemEntities(orderItemEntities);


        OrderEntity savedOrderEntity = orderJpaRepository.save(orderEntity);

        return toModel(savedOrderEntity);
    }

    private List<OrderItemEntity> createOrderItemEntities(OrderEntity orderEntity, OrderCreate orderCreate) {
        return orderCreate.getOrderItems().stream().map(orderItem -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrderItemId(orderItem.getId());
            orderItemEntity.setPrice(orderItem.getPrice());
            orderItemEntity.setOrderEntity(orderEntity);

            return orderItemEntity;
        }).collect(Collectors.toList());
    }

    private Order toModel(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .price(orderEntity.getPrice())
                .createdDate(orderEntity.getCreatedAt())
                .build();
    }
}