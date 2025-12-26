package com.example.youyoung.order.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Long productId;
    private String productName;
    private LocalDateTime orderDate;
}
