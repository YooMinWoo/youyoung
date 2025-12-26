package com.example.youyoung.product.controller;

import com.example.youyoung.global.ApiResponse;
import com.example.youyoung.product.dto.ProductListResponse;
import com.example.youyoung.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 목록 조회", description = "상품 목록 조회 API (페이징 처리)")
    @GetMapping
    public ResponseEntity<ApiResponse<ProductListResponse>> getProducts(@RequestParam(name = "page", required = false) Integer page){
        ProductListResponse response = productService.getProducts(page);
        return ResponseEntity.ok(ApiResponse.success("상품 목록 조회 성공",response));
    }
}
