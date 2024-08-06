package com.wms.global.exception.exception;

import com.wms.global.exception.responseCode.UserExceptionResponseCode;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException{
    private UserExceptionResponseCode exceptionCode;

    private String log;

    public UserException(UserExceptionResponseCode exceptionCode, String log){
        this.exceptionCode = exceptionCode;
        this.log = log;
    }
}
