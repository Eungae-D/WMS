package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum InventoryExceptionResponseCode {

    //404 NOT_FOUND
    INVENTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Inventory-001", "재고를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    InventoryExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
