package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum LotExceptionResponseCode {

    //409 CONFLICT
    LOT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Lot-001", "로트 번호가 이미 존재합니다."),

    //404 NOTFOUND
    LOT_NOT_FOUND(HttpStatus.CONFLICT, "Lot-002", "로트를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    LotExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
