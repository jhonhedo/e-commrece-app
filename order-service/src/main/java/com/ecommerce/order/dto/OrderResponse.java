package com.ecommerce.order.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Long id,
        Long customerId,
        BigDecimal totalAmount,
        String status,
        List<OrderItemResponse> items

) {
}
