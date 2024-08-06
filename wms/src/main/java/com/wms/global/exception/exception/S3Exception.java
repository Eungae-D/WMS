package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.S3ExceptionResponseCode;
import lombok.Getter;

@Getter
public class S3Exception extends RuntimeException{
    private S3ExceptionResponseCode exceptionCode;

    private String log;

    public S3Exception(S3ExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
