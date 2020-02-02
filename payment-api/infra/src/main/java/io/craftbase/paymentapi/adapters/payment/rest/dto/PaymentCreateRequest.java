package io.craftbase.paymentapi.adapters.payment.rest.dto;

import io.craftbase.paymentapi.payment.model.PaymentCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreateRequest {

    @NotNull
    private Long cardId;

    @NotNull
    private BigDecimal price;

    @NotEmpty
    private String referenceCode;

    public PaymentCreate toModel() {
        return PaymentCreate.builder()
                .price(price)
                .cardId(cardId)
                .referenceCode(referenceCode)
                .build();
    }
}