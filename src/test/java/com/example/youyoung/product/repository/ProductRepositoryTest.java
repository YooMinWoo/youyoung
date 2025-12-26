package com.example.youyoung.product.repository;

import com.example.youyoung.product.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class ProductRepositoryTest {

    @Autowired ProductRepository productRepository;

    @Test
    void 상품_조회_테스트(){
        // given
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("quantity").ascending());

        // when
        Page<Product> products = productRepository.getProducts(pageRequest);

        // then
        assertThat(products.getSize()).isEqualTo(10);
        assertThat(products.getTotalElements()).isEqualTo(25);
        assertThat(products.getTotalPages()).isEqualTo(3);
    }
}
