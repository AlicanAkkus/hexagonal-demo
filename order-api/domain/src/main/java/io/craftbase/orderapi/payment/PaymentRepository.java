package io.craftbase.orderapi.payment;

import io.craftbase.orderapi.payment.model.Payment;

import java.math.BigDecimal;

public interface PaymentRepository {

    Payment pay(BigDecimal price, String referenceCode);
}
