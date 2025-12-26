package com.example.youyoung.order.repository;

import com.example.youyoung.order.domain.Order;
import com.example.youyoung.order.domain.OrderStatus;
import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @SqlGroup({
            @Sql(value = "/sql/주문_더미데이터.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void 주문_내역_조회_테스트(){
        // given
        Long userId = 1L;
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("createdTime").ascending());

        // when
        Page<Order> orders = orderRepository.getOrders(userId, pageRequest);

        // then
        assertThat(orders.getSize()).isEqualTo(10);
        assertThat(orders.getTotalElements()).isEqualTo(25);
        assertThat(orders.getTotalPages()).isEqualTo(3);
    }

    @Test
    @SqlGroup({
            @Sql(value = "/sql/주문_더미데이터.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void 단건_조회_테스트(){
        // given
        Long orderId = 1L;
        Long userId = 1L;

        // when
        Order order = orderRepository.getOrder(orderId, userId);

        // then
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
        assertThat(order.getUser().getId()).isEqualTo(userId);
    }
}
