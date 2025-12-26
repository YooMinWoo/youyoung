package com.example.youyoung.order.dto;

import com.example.youyoung.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Long productId;
    private String productName;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
}
