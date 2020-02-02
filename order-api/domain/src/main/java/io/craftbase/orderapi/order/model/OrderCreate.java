package io.craftbase.orderapi.order.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderCreate {

    private Long cardId;
    private String note;
    private String address;
    private BigDecimal price;
    private String referenceCode;
    private List<OrderItem> orderItems;
}