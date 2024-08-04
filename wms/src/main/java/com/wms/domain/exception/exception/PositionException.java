package com.wms.domain.exception.exception;

import com.wms.domain.exception.responseCode.PositionExceptionResponseCode;
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
