package com.example.youyoung.order.service;

import com.example.youyoung.order.domain.Order;
import com.example.youyoung.order.dto.CreateOrderRequest;
import com.example.youyoung.order.repository.OrderJpaRepository;
import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class OrderConcurrencyTest {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderJpaRepository orderJpaRepository;

    @Test
    @DisplayName("동시성 제어 없이 동시 주문 - 재고 문제 발생")
    void createOrder_withoutLock_concurrencyIssue() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

        Runnable orderTask = () -> {
            try {
                CreateOrderRequest request = new CreateOrderRequest(1L, 1L);
                orderService.createOrder(request);
            } finally {
                latch.countDown();
            }
        };

        executor.submit(orderTask);
        executor.submit(orderTask);

        latch.await();
        executor.shutdown();

        Product updatedProduct = productService.getProduct(1L);
        List<Order> orders = orderJpaRepository.findAll();

        assertThat(updatedProduct.getQuantity()).isEqualTo(-1);
        assertThat(orders.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("동시성 제어 적용")
    void createOrderWithLock() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

        Runnable orderTask = () -> {
            try {
                CreateOrderRequest request = new CreateOrderRequest(1L, 1L);
                orderService.createOrderWithLock(request);
            } finally {
                latch.countDown();
            }
        };

        executor.submit(orderTask);
        executor.submit(orderTask);

        latch.await();
        executor.shutdown();

        Product updatedProduct = productService.getProduct(1L);
        List<Order> orders = orderJpaRepository.findAll();

        assertThat(updatedProduct.getQuantity()).isEqualTo(0);
        assertThat(orders.size()).isEqualTo(1);
    }
}
