package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.InventoryExceptionResponseCode;
import lombok.Getter;

@Getter
public class InventoryException extends RuntimeException{
    private InventoryExceptionResponseCode exceptionCode;

    private String log;

    public InventoryException(InventoryExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
