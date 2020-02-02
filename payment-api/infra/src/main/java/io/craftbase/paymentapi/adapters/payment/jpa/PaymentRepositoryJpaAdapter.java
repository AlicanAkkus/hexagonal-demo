package io.craftbase.paymentapi.adapters.payment.jpa;

import io.craftbase.paymentapi.adapters.payment.jpa.entity.PaymentEntity;
import io.craftbase.paymentapi.payment.PaymentRepository;
import io.craftbase.paymentapi.payment.model.Payment;
import io.craftbase.paymentapi.payment.model.PaymentCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentRepositoryJpaAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment pay(PaymentCreate paymentCreate) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setCardId(paymentCreate.getCardId());
        paymentEntity.setPrice(paymentCreate.getPrice());
        paymentEntity.setReferenceCode(paymentCreate.getReferenceCode());
        paymentEntity.setInstallment(0);

        PaymentEntity savedPaymentEntity = paymentJpaRepository.save(paymentEntity);
        return toModel(savedPaymentEntity);
    }

    private Payment toModel(PaymentEntity paymentEntity) {
        return Payment.builder()
                .id(paymentEntity.getId())
                .price(paymentEntity.getPrice())
                .cardId(paymentEntity.getCardId())
                .installment(paymentEntity.getInstallment())
                .referenceCode(paymentEntity.getReferenceCode())
                .build();
    }
}