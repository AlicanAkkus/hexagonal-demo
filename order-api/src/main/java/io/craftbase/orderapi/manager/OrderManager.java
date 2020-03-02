package io.craftbase.orderapi.manager;

import io.craftbase.orderapi.converter.OrderCreateRequestToVoConverter;
import io.craftbase.orderapi.model.request.OrderCreateRequest;
import io.craftbase.orderapi.service.OrderService;
import io.craftbase.orderapi.vo.OrderCreateVo;
import io.craftbase.orderapi.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderManager {

    private final OrderService orderService;
    private final OrderCreateRequestToVoConverter orderCreateRequestToVoConverter;

    @Transactional
    public OrderVo create(OrderCreateRequest orderCreateRequest) {
        OrderCreateVo orderCreateVo = orderCreateRequestToVoConverter.convertToVo(orderCreateRequest);

        OrderVo orderVo = orderService.create(orderCreateVo);

        log.info("Order create has completed with id: {}", orderVo.getId());
        return orderVo;
    }
}