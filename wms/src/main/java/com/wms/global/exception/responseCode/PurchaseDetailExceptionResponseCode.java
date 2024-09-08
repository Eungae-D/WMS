package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum PurchaseDetailExceptionResponseCode {
    //404 NOTFOUND
    PURCHASE_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "PurchaseDetail-001", "발주 상세를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    PurchaseDetailExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
