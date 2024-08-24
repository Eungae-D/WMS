package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.ClientExceptionResponseCode;
import lombok.Getter;

@Getter
public class ClientException extends RuntimeException{
    private ClientExceptionResponseCode exceptionCode;

    private String log;

    public ClientException(ClientExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
