package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.TokenExceptionResponseCode;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException{
    private TokenExceptionResponseCode exceptionCode;

    private String log;

    public TokenException(TokenExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
