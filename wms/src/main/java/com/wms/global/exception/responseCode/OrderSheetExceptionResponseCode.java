package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum OrderSheetExceptionResponseCode {

    //404 NOTFOUND
    ORDER_DETAILS_EMPTY(HttpStatus.NOT_FOUND, "OrderSheet-001", "수주서에 품목이 존재하지 않습니다."),
    ORDER_SHEETS_EMPTY(HttpStatus.NOT_FOUND, "OrderSheet-002", "수주서가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    OrderSheetExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
