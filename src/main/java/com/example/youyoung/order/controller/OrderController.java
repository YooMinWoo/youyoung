package com.example.youyoung.order.controller;

import com.example.youyoung.global.ApiResponse;
import com.example.youyoung.order.dto.CreateOrderRequest;
import com.example.youyoung.order.dto.OrderListResponse;
import com.example.youyoung.order.dto.UserInfoRequest;
import com.example.youyoung.order.service.OrderService;
import com.example.youyoung.product.dto.ProductListResponse;
import com.example.youyoung.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성", description = "주문 생성 API")
    @PostMapping
    public ResponseEntity<ApiResponse<?>> getProducts(CreateOrderRequest request){
        orderService.createOrder(request);
        return ResponseEntity.ok(ApiResponse.success("주문 생성 성공",null));
    }

    @Operation(summary = "주문 내역 조회", description = "주문 내역 조회 API (페이징 처리)")
    @GetMapping
    public ResponseEntity<ApiResponse<OrderListResponse>> getProducts(@RequestBody UserInfoRequest request,
                                                                      @RequestParam(name = "page", required = false) Integer page){
        OrderListResponse orders = orderService.getOrders(request, page);
        return ResponseEntity.ok(ApiResponse.success("주문 내역 조회 성공", orders));
    }
}
