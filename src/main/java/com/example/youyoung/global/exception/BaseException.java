package com.example.youyoung.global.exception;

import com.example.youyoung.global.exception.code.BaseErrorCode;
import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException{

    private final BaseErrorCode code;

    protected BaseException(BaseErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
}
