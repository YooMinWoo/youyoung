package com.example.youyoung.global.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCode implements BaseErrorCode{

    PRODUCT_QUANTITY_BAD_REQUEST(HttpStatus.BAD_REQUEST, "PRODUCT_001", "해당 상품은 주문이 불가능합니다"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRODUCT_002", "존재하지 않는 상품입니다");

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
