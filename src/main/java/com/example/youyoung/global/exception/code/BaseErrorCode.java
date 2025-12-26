package com.example.youyoung.global.exception.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getHttpStatus();
    String getCustomCode();
    String getMessage();
}
