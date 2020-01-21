package io.craftbase.orderapi.order.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Order {

    private Long id;
    private BigDecimal price;
    private String address;
    private String note;
    private List<Long> orderItemIds;
    private LocalDateTime createdDate;
}