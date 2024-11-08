package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.LotExceptionResponseCode;
import lombok.Getter;

@Getter
public class LotException extends RuntimeException {
    private LotExceptionResponseCode exceptionCode;

    private String log;

    public LotException(LotExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
