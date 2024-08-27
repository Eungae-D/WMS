package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.AreaExceptionResponseCode;
import lombok.Getter;

@Getter
public class AreaException extends RuntimeException{
    private AreaExceptionResponseCode exceptionCode;

    private String log;

    public AreaException(AreaExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
