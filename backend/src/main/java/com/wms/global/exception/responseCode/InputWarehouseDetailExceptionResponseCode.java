package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum InputWarehouseDetailExceptionResponseCode {
    //404 NOTFOUND
    INPUT_WAREHOUSE_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "InputWarehouseDetail-001", "입고서 상세 정보를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    InputWarehouseDetailExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
