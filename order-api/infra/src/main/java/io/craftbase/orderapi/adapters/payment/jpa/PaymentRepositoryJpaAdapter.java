package io.craftbase.orderapi.adapters.payment.jpa;

import io.craftbase.orderapi.payment.PaymentRepository;
import io.craftbase.orderapi.adapters.payment.jpa.entity.PaymentEntity;
import io.craftbase.orderapi.payment.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentRepositoryJpaAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment pay(BigDecimal price, String referenceCode) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPrice(price);
        paymentEntity.setReferenceCode(referenceCode);
        paymentEntity.setInstallment(0);

        PaymentEntity savedPaymentEntity = paymentJpaRepository.save(paymentEntity);
        return toModel(savedPaymentEntity);
    }

    private Payment toModel(PaymentEntity paymentEntity) {
        return Payment.builder()
                .id(paymentEntity.getId())
                .price(paymentEntity.getPrice())
                .installment(paymentEntity.getInstallment())
                .referenceCode(paymentEntity.getReferenceCode())
                .build();
    }
}