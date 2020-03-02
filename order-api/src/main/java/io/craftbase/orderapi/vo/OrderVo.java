package io.craftbase.orderapi.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderVo {

    private Long id;
    private BigDecimal price;
    private LocalDateTime createdDate;
}