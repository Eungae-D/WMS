package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ItemExceptionResponseCode {

    //409 CONFLICT
    ITEM_CODE_DUPLICATE(HttpStatus.CONFLICT, "Item-001", "품목 코드가 이미 존재합니다."),
    ITEM_NAME_DUPLICATE(HttpStatus.CONFLICT, "Item-001", "상품명이 이미 존재합니다."),

    //404 NOTFOUND
    ITEM_NOT_FOUND(HttpStatus.CONFLICT, "Item-002", "상품을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ItemExceptionResponseCode(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
