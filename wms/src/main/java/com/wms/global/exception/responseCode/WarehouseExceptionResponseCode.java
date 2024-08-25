package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum WarehouseExceptionResponseCode {
    //409 CONFLICT
    WAREHOUSE_CODE_DUPLICATE(HttpStatus.CONFLICT, "Warehouse-001", "창고 코드가 이미 존재합니다."),
    WAREHOUSE_NAME_DUPLICATE(HttpStatus.CONFLICT, "Warehouse-003", "이미 존재하는 창고 이름입니다."),

    //404 NOTFOUND
    WAREHOUSE_NOT_FOUND(HttpStatus.CONFLICT, "Warehouse-002", "창고를 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;

    WarehouseExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
