package io.craftbase.paymentapi.contract.base;

import io.craftbase.paymentapi.payment.model.Payment;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BasePaymentContractTest extends AbstractContractTest {

    @Override
    void setUp() {
        when(paymentFacade.pay(any())).thenReturn(Payment.builder()
                .id(1L)
                .cardId(1L)
                .price(BigDecimal.TEN)
                .referenceCode("ref")
                .build());
    }
}