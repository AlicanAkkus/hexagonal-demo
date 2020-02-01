package io.craftbase.orderapi.payment;

import io.craftbase.orderapi.payment.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentRepository paymentRepository;

    public Payment pay(BigDecimal price, String referenceCode) {
        return paymentRepository.pay(price, referenceCode);
    }
}