package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.CellExceptionResponseCode;
import lombok.Getter;

@Getter
public class CellException extends RuntimeException{
    private CellExceptionResponseCode exceptionCode;

    private String log;

    public CellException(CellExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
