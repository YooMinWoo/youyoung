package com.example.youyoung.order.domain;

import com.example.youyoung.global.BaseTimeEntity;
import com.example.youyoung.order.dto.CreateOrderRequest;
import com.example.youyoung.product.domain.Product;
import com.example.youyoung.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Order create(Product product, User user){
        return Order.builder()
                .product(product)
                .user(user)
                .build();
    }
}
