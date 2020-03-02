package io.craftbase.orderapi.contract.base;

import io.craftbase.orderapi.vo.OrderVo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BaseOrderContractTest extends AbstractContractTest {

    @Override
    void setUp() {
        when(orderManager.create(any())).thenReturn(OrderVo.builder()
                .id(1L)
                .price(BigDecimal.valueOf(10.00))
                .createdDate(LocalDateTime.of(2020, 2, 1, 10, 21, 47))
                .build());
    }
}