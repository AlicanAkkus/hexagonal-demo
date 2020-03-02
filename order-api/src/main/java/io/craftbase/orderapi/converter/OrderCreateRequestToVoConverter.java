package io.craftbase.orderapi.converter;

import io.craftbase.orderapi.model.request.OrderCreateRequest;
import io.craftbase.orderapi.model.request.OrderItemDto;
import io.craftbase.orderapi.vo.OrderCreateVo;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderCreateRequestToVoConverter {

    public OrderCreateVo convertToVo(OrderCreateRequest orderCreateRequest) {
        return OrderCreateVo.builder()
                .note(orderCreateRequest.getNote())
                .price(orderCreateRequest.getPrice())
                .address(orderCreateRequest.getAddress())
                .referenceCode(UUID.randomUUID().toString())
                .orderItemVos(orderCreateRequest.getOrderItems().stream().map(OrderItemDto::toModel).collect(Collectors.toList()))
                .build();
    }
}