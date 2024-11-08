package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.OrderSheetExceptionResponseCode;
import lombok.Getter;

@Getter
public class OrderSheetException extends RuntimeException{
    private OrderSheetExceptionResponseCode exceptionCode;

    private String log;

    public OrderSheetException(OrderSheetExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
