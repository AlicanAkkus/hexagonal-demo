package io.craftbase.orderapi.service;

import io.craftbase.orderapi.converter.PaymentEntityToVoConverter;
import io.craftbase.orderapi.repository.PaymentEntity;
import io.craftbase.orderapi.repository.PaymentRepositoryJpaAdapter;
import io.craftbase.orderapi.vo.PaymentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepositoryJpaAdapter paymentRepositoryJpaAdapter;
    private final PaymentEntityToVoConverter paymentEntityToVoConverter;

    public PaymentVo pay(BigDecimal price, String referenceCode) {
        PaymentEntity paymentEntity = paymentRepositoryJpaAdapter.pay(price, referenceCode);
        return paymentEntityToVoConverter.convertToVo(paymentEntity);
    }
}