package com.example.youyoung.product.dto.mapper;

import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.dto.ProductListResponse;
import com.example.youyoung.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ProductResponseMapper {

    public static List<ProductResponse> from(List<Product> products){

        return products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
