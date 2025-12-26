package com.example.youyoung.order.service;

import com.example.youyoung.order.domain.Order;
import com.example.youyoung.order.domain.OrderStatus;
import com.example.youyoung.order.dto.CreateOrderRequest;
import com.example.youyoung.order.repository.OrderRepository;
import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.repository.ProductRepository;
import com.example.youyoung.product.service.ProductService;
import com.example.youyoung.user.domain.User;
import com.example.youyoung.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks OrderService orderService;

    @Test
    void 주문을_정상적으로_생성한다() {
        // given
        CreateOrderRequest request = new CreateOrderRequest(1L, 1L);

        User userProxy = mock(User.class);
        Product product = new Product(100L, "상품", 10000, 10);

        given(userService.getUserProxy(1L)).willReturn(userProxy);
        given(productService.getProduct(1L)).willReturn(product);

        // when
        orderService.createOrder(request);

        // then
        assertThat(product.getQuantity()).isEqualTo(9);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void 재고가_없으면_예외가_발생한다() {
        // given
        CreateOrderRequest request = new CreateOrderRequest(1L, 1L);

        User userProxy = mock(User.class);
        Product product = new Product(100L, "상품", 10000, 0);

        given(userService.getUserProxy(1L)).willReturn(userProxy);
        given(productService.getProduct(1L)).willReturn(product);

        // when & then
        assertThatThrownBy(() -> orderService.createOrder(request))
                .isInstanceOf(RuntimeException.class);

        verify(orderRepository, never()).save(any());
    }
}
