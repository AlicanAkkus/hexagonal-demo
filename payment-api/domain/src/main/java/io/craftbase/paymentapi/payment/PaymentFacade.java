package io.craftbase.paymentapi.payment;

import io.craftbase.paymentapi.payment.model.Payment;
import io.craftbase.paymentapi.payment.model.PaymentCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentRepository paymentRepository;

    public Payment pay(PaymentCreate paymentCreate) {
        return paymentRepository.pay(paymentCreate);
    }
}