package com.example.youyoung.order.repository;

import com.example.youyoung.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT o FROM Order o
            JOIN FETCH o.product p
            WHERE o.user.id = :userId
            """)
    Page<Order> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("""
            SELECT o FROM Order o
            JOIN FETCH o.product p
            WHERE o.id = :orderId
            AND o.user.id = :userId
            """)
    Order getOrder(@Param("orderId") Long orderId, @Param("userId") Long userId);
}
