package com.wms.global.exception.exception;

import org.springframework.http.HttpStatus;

public interface CommonException {
    String getExceptionMessage();

    String getLog();

    HttpStatus getStatus();

    String getCode();
}
