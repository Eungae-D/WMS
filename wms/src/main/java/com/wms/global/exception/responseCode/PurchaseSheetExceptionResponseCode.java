package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum PurchaseSheetExceptionResponseCode {

    //404 NOTFOUND
    PURCHASE_SHEET_NOT_FOUND(HttpStatus.NOT_FOUND, "PurchaseSheet-001", "발주서를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    PurchaseSheetExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
