package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.RackExceptionResponseCode;
import lombok.Getter;

@Getter
public class RackException extends RuntimeException{
    private RackExceptionResponseCode exceptionCode;

    private String log;

    public RackException(RackExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
