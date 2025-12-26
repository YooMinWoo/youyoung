package com.example.youyoung.order.dto.mapper;

import com.example.youyoung.order.domain.Order;
import com.example.youyoung.order.dto.OrderListResponse;
import com.example.youyoung.order.dto.OrderResponse;
import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.dto.ProductListResponse;
import com.example.youyoung.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class OrderListResponseMapper {

    public static OrderListResponse from(Page<Order> orders){
        List<OrderResponse> items = OrderResponseMapper.from(orders.getContent());

        return OrderListResponse.builder()
                .items(items)
                .page(orders.getNumber())
                .size(orders.getNumberOfElements())
                .totalElements(orders.getTotalElements())
                .totalPages(orders.getTotalPages())
                .hasNext(orders.hasNext())
                .build();
    }
}
