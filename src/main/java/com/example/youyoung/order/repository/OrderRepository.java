package com.example.youyoung.order.repository;

import com.example.youyoung.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public void save(Order order){
        orderJpaRepository.save(order);
    }
}
