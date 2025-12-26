package com.example.youyoung.product.service;

import com.example.youyoung.product.domain.Product;
import com.example.youyoung.product.dto.ProductListResponse;
import com.example.youyoung.product.dto.mapper.ProductListResponseMapper;
import com.example.youyoung.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductListResponse getProducts(Integer page){
        // 페이징 처리의 경우 10개씩 페이징, 개수가 적은 순으로 정렬
        if(page == null || page <= 0) page = 1;
        page -= 1;
        Pageable pageable = PageRequest.of(page, 10, Sort.by("quantity").ascending());

        Page<Product> products = productRepository.getProducts(pageable);
        return ProductListResponseMapper.from(products);
    }
}
