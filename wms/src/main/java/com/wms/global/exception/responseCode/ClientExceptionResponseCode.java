package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ClientExceptionResponseCode {

    //409 CONFLICT
    CLIENT_CODE_DUPLICATE(HttpStatus.CONFLICT, "Client-001", "거래처 코드가 이미 존재합니다."),

    //404 NOTFOUND
    CLIENT_NOT_FOUND(HttpStatus.CONFLICT, "Client-002", "거래처를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ClientExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
