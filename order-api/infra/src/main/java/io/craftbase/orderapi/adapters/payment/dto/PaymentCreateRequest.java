package io.craftbase.orderapi.adapters.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentCreateRequest {

    private Long cardId;
    private BigDecimal price;
    private String referenceCode;
}