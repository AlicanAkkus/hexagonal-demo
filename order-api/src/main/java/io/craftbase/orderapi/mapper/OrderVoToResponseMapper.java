package io.craftbase.orderapi.mapper;

import io.craftbase.orderapi.model.request.OrderResponse;
import io.craftbase.orderapi.vo.OrderVo;
import org.springframework.stereotype.Component;

@Component
public class OrderVoToResponseMapper {

    public OrderResponse map(OrderVo orderVo) {
        return OrderResponse.builder()
                .id(orderVo.getId())
                .price(orderVo.getPrice())
                .createdDate(orderVo.getCreatedDate())
                .build();
    }
}
