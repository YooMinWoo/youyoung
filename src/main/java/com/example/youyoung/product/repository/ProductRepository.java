package com.example.youyoung.product.repository;

import com.example.youyoung.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    public Page<Product> getProducts(Pageable pageable){
        return productJpaRepository.findAll(pageable);
    }

    public Product findById(Long productId) {
        return productJpaRepository.findById(productId).orElseThrow();
    }
}
