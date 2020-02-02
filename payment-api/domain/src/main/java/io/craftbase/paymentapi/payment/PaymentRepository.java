package io.craftbase.paymentapi.payment;

import io.craftbase.paymentapi.payment.model.Payment;
import io.craftbase.paymentapi.payment.model.PaymentCreate;

import java.math.BigDecimal;

public interface PaymentRepository {

    Payment pay(PaymentCreate paymentCreate);
}
