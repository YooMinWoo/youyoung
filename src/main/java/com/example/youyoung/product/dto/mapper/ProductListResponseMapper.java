package com.example.youyoung.product.dto.mapper;

import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.dto.ProductListResponse;
import com.example.youyoung.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductListResponseMapper {

    public static ProductListResponse from(Page<Product> products){
        List<ProductResponse> items = ProductResponseMapper.from(products.getContent());

        return ProductListResponse.builder()
                .items(items)
                .page(products.getNumber())
                .size(products.getNumberOfElements())
                .totalElements(products.getTotalElements())
                .totalPages(products.getTotalPages())
                .hasNext(products.hasNext())
                .build();
    }
}
