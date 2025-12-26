package com.example.youyoung.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private int price;
    private int quantity;

}
