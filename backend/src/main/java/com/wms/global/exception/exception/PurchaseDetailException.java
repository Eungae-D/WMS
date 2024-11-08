package com.wms.global.exception.exception;


import com.wms.global.exception.responseCode.PurchaseDetailExceptionResponseCode;
import lombok.Getter;

@Getter
public class PurchaseDetailException extends RuntimeException{
    private PurchaseDetailExceptionResponseCode exceptionCode;

    private String log;

    public PurchaseDetailException(PurchaseDetailExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
