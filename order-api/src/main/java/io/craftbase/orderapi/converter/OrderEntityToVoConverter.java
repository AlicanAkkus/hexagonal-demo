package io.craftbase.orderapi.converter;

import io.craftbase.orderapi.repository.OrderEntity;
import io.craftbase.orderapi.vo.OrderVo;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityToVoConverter {

    public OrderVo convertToVo(OrderEntity orderEntity) {
        return OrderVo.builder()
                .id(orderEntity.getId())
                .price(orderEntity.getPrice())
                .createdDate(orderEntity.getCreatedAt())
                .build();
    }
}