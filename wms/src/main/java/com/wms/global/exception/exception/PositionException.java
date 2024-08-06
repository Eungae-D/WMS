package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.PositionExceptionResponseCode;
import lombok.Getter;

@Getter
public class PositionException extends RuntimeException{
    private PositionExceptionResponseCode exceptionCode;

    private String log;

    public PositionException(PositionExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
