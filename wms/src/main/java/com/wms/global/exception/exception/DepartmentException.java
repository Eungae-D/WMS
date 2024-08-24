package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.DepartmentExceptionResponseCode;
import lombok.Getter;

@Getter
public class DepartmentException extends RuntimeException{
    private DepartmentExceptionResponseCode exceptionCode;

    private String log;

    public DepartmentException(DepartmentExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
