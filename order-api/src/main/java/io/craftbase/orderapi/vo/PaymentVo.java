package io.craftbase.orderapi.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentVo {

    private Long id;
    private BigDecimal price;
    private String referenceCode;
    private Integer installment;
}