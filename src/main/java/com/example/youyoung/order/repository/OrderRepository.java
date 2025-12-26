package com.example.youyoung.order.repository;

import com.example.youyoung.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public void save(Order order){
        orderJpaRepository.save(order);
    }

    public Page<Order> getOrders(Long userId, Pageable pageable){
        return orderJpaRepository.findAllByUserId(userId, pageable);
    }
}
