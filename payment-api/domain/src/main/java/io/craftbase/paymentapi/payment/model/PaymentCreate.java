package io.craftbase.paymentapi.payment.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentCreate {

    private Long cardId;
    private BigDecimal price;
    private String referenceCode;
}