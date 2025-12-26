package com.example.youyoung.global.exception;


import com.example.youyoung.global.exception.code.BaseErrorCode;

public class ApplicationException extends BaseException{

    public ApplicationException(BaseErrorCode code){ super(code);}
}
