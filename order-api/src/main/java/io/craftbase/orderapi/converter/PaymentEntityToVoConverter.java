package io.craftbase.orderapi.converter;

import io.craftbase.orderapi.repository.PaymentEntity;
import io.craftbase.orderapi.vo.PaymentVo;
import org.springframework.stereotype.Component;

@Component
public class PaymentEntityToVoConverter {

    public PaymentVo convertToVo(PaymentEntity paymentEntity) {
        return PaymentVo.builder()
                .id(paymentEntity.getId())
                .price(paymentEntity.getPrice())
                .installment(paymentEntity.getInstallment())
                .referenceCode(paymentEntity.getReferenceCode())
                .build();
    }
}