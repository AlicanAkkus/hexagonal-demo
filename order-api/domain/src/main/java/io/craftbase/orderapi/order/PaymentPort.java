package io.craftbase.orderapi.order;

import io.craftbase.orderapi.order.model.Payment;

import java.math.BigDecimal;

public interface PaymentPort {

    Payment pay(Long cardId, BigDecimal price, String referenceCode);
}
