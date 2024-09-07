package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.PurchaseSheetExceptionResponseCode;
import lombok.Getter;

@Getter
public class PurchaseSheetException extends RuntimeException{
    private PurchaseSheetExceptionResponseCode exceptionCode;

    private String log;

    public PurchaseSheetException(PurchaseSheetExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
