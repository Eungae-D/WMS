package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.InputWarehouseDetailExceptionResponseCode;
import lombok.Getter;

@Getter
public class InputWarehouseDetailException extends RuntimeException {
    private InputWarehouseDetailExceptionResponseCode exceptionCode;

    private String log;

    public InputWarehouseDetailException(InputWarehouseDetailExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
