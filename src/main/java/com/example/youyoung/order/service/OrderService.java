package com.example.youyoung.order.service;

import com.example.youyoung.global.exception.ApplicationException;
import com.example.youyoung.global.exception.code.ProductErrorCode;
import com.example.youyoung.order.domain.Order;
import com.example.youyoung.order.dto.CreateOrderRequest;
import com.example.youyoung.order.dto.OrderListResponse;
import com.example.youyoung.order.dto.UserInfoRequest;
import com.example.youyoung.order.dto.mapper.OrderListResponseMapper;
import com.example.youyoung.order.repository.OrderRepository;
import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.service.ProductService;
import com.example.youyoung.user.domain.User;
import com.example.youyoung.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;
    private final ProductService productService;

    @Transactional
    public void createOrder(CreateOrderRequest request){
        User userProxy = userService.getUserProxy(request.userId());
        Product product = productService.getProduct(request.productId());

        if(product.getQuantity() <= 0) throw new ApplicationException(ProductErrorCode.PRODUCT_QUANTITY_BAD_REQUEST);
        product.deductQuantity();

        Order order = Order.create(product, userProxy);
        orderRepository.save(order);
    }

    public OrderListResponse getOrders(UserInfoRequest request, Integer page){
        // 페이징 처리의 경우 10개씩 페이징, 최신 주문 순으로 정렬
        if(page == null || page <= 0) page = 1;
        page -= 1;
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdTime").descending());

        Page<Order> orders = orderRepository.getOrders(request.userId(), pageable);
        return OrderListResponseMapper.from(orders);
    }

    @Transactional
    public void cancelOrder(UserInfoRequest request, Long orderId) {
        Order order = orderRepository.getOrder(orderId, request.userId());
        order.cancel();
    }
}
