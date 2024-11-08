package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.ItemExceptionResponseCode;
import lombok.Getter;

@Getter
public class ItemException extends RuntimeException{
    private ItemExceptionResponseCode exceptionCode;

    private String log;

    public ItemException(ItemExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
