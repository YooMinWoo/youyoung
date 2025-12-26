package com.example.youyoung.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    COMPLETED("완료"),
    CANCEL("취소")
    ;

    private final String description;

}
