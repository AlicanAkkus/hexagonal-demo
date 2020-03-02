package io.craftbase.orderapi.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderCreateVo {

    private String note;
    private String address;
    private BigDecimal price;
    private String referenceCode;
    private List<OrderItemVo> orderItemVos;
}