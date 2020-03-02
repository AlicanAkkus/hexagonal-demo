package io.craftbase.orderapi.service;

import io.craftbase.orderapi.converter.OrderEntityToVoConverter;
import io.craftbase.orderapi.repository.OrderEntity;
import io.craftbase.orderapi.repository.OrderRepositoryJpaAdapter;
import io.craftbase.orderapi.validator.OrderCreateValidator;
import io.craftbase.orderapi.vo.OrderCreateVo;
import io.craftbase.orderapi.vo.OrderVo;
import io.craftbase.orderapi.vo.PaymentVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderCreateValidator orderCreateValidator;
    private final PaymentService paymentService;
    private final OrderRepositoryJpaAdapter orderRepositoryJpaAdapter;
    private final OrderEntityToVoConverter orderEntityToVoConverter;

    public OrderVo create(OrderCreateVo orderCreateVo) {
        orderCreateValidator.validate(orderCreateVo);

        PaymentVo paymentVo = paymentService.pay(orderCreateVo.getPrice(), orderCreateVo.getReferenceCode());
        log.debug("Pay completed with reference code {} and price {}", paymentVo.getReferenceCode(), paymentVo.getPrice());

        OrderEntity orderEntity = orderRepositoryJpaAdapter.save(orderCreateVo);

        log.info("Order has completed with id: {}", orderEntity.getId());
        return orderEntityToVoConverter.convertToVo(orderEntity);
    }
}