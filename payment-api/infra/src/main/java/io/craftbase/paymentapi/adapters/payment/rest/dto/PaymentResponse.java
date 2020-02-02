package io.craftbase.paymentapi.adapters.payment.rest.dto;

import io.craftbase.paymentapi.payment.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long id;
    private Long cardId;
    private BigDecimal price;
    private String referenceCode;

    public static PaymentResponse fromModel(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .price(payment.getPrice())
                .cardId(payment.getCardId())
                .referenceCode(payment.getReferenceCode())
                .build();
    }
}