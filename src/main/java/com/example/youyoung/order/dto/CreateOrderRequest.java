package com.example.youyoung.order.dto;

public record CreateOrderRequest(
        Long userId,
        Long productId
){}
