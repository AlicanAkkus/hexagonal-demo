package io.craftbase.orderapi.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentRepositoryJpaAdapter {

    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentEntity pay(BigDecimal price, String referenceCode) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPrice(price);
        paymentEntity.setReferenceCode(referenceCode);
        paymentEntity.setInstallment(0);

        return paymentJpaRepository.save(paymentEntity);
    }
}