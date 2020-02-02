package io.craftbase.paymentapi.contract.base;

import io.craftbase.paymentapi.order.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BaseOrderContractTest extends AbstractContractTest {

    @Override
    void setUp() {
        when(orderFacade.create(any())).thenReturn(Order.builder()
                .id(1L)
                .price(BigDecimal.valueOf(30.00))
                .createdDate(LocalDateTime.of(2020, 2, 1, 10, 21, 47))
                .build());
    }
}