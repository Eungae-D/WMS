package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.WarehouseExceptionResponseCode;
import lombok.Getter;

@Getter
public class WarehouseException extends RuntimeException{
    private WarehouseExceptionResponseCode exceptionCode;

    private String log;

    public WarehouseException(WarehouseExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
