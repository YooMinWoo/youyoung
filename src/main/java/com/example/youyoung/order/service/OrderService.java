package com.example.youyoung.order.service;

import com.example.youyoung.global.exception.ApplicationException;
import com.example.youyoung.global.exception.code.ProductErrorCode;
import com.example.youyoung.order.domain.Order;
import com.example.youyoung.order.dto.CreateOrderRequest;
import com.example.youyoung.order.repository.OrderRepository;
import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.service.ProductService;
import com.example.youyoung.user.domain.User;
import com.example.youyoung.user.repository.UserRepository;
import com.example.youyoung.user.service.UserService;
import lombok.RequiredArgsConstructor;
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
}
