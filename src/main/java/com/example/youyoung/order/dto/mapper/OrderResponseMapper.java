package com.example.youyoung.order.dto.mapper;

import com.example.youyoung.order.domain.Order;
import com.example.youyoung.order.dto.OrderResponse;
import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.dto.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

public class OrderResponseMapper {

    public static List<OrderResponse> from(List<Order> orders){
        return orders.stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .productId(order.getProduct().getId())
                        .productName(order.getProduct().getName())
                        .orderDate(order.getCreatedTime())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
