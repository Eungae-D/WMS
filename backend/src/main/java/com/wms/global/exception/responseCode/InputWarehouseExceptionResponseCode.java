package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum InputWarehouseExceptionResponseCode {

    //404 NOTFOUND
    INPUT_WAREHOUSE_NOT_FOUND(HttpStatus.NOT_FOUND, "InputWarehouse-001", "입고서 정보를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    InputWarehouseExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
