package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.InputWarehouseExceptionResponseCode;
import lombok.Getter;

@Getter
public class InputWarehouseException extends RuntimeException{
    private InputWarehouseExceptionResponseCode exceptionCode;

    private String log;

    public InputWarehouseException(InputWarehouseExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
