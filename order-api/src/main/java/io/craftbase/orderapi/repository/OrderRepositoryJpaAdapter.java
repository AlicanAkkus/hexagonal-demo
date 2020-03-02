package io.craftbase.orderapi.repository;

import io.craftbase.orderapi.vo.OrderCreateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderRepositoryJpaAdapter {

    private final OrderJpaRepository orderJpaRepository;

    public OrderEntity save(OrderCreateVo orderCreateVo) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setAddress(orderCreateVo.getAddress());
        orderEntity.setNote(orderCreateVo.getNote());
        orderEntity.setPrice(orderCreateVo.getPrice());
        orderEntity.setReferenceCode(orderCreateVo.getReferenceCode());

        List<OrderItemEntity> orderItemEntities = createOrderItemEntities(orderEntity, orderCreateVo);
        orderEntity.setOrderItemEntities(orderItemEntities);


        return orderJpaRepository.save(orderEntity);
    }

    private List<OrderItemEntity> createOrderItemEntities(OrderEntity orderEntity, OrderCreateVo orderCreateVo) {
        return orderCreateVo.getOrderItemVos().stream().map(orderItem -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrderItemId(orderItem.getId());
            orderItemEntity.setPrice(orderItem.getPrice());
            orderItemEntity.setOrderEntity(orderEntity);

            return orderItemEntity;
        }).collect(Collectors.toList());
    }
}