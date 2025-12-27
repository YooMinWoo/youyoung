package com.example.youyoung.product.repository;

import com.example.youyoung.global.exception.ApplicationException;
import com.example.youyoung.global.exception.code.ProductErrorCode;
import com.example.youyoung.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    public Page<Product> getProducts(Pageable pageable){
        return productJpaRepository.findAll(pageable);
    }

    public Product findById(Long productId) {

        return productJpaRepository.findById(productId)
                .orElseThrow(() -> new ApplicationException(ProductErrorCode.PRODUCT_NOT_FOUND));
    }

    public void decreaseQuantity(Long productId) {
        productJpaRepository.decreaseQuantity(productId);
    }

    public Product findByIdWithPessimisticLock(Long productId) {
        return productJpaRepository.findByIdWithPessimisticLock(productId)
                .orElseThrow(() -> new ApplicationException(ProductErrorCode.PRODUCT_NOT_FOUND));
    }
}
