package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AreaExceptionResponseCode {
    //409 CONFLICT
    AREA_CODE_DUPLICATE(HttpStatus.CONFLICT, "Area-001", "구역 코드가 이미 존재합니다."),
    AREA_NAME_DUPLICATE(HttpStatus.CONFLICT, "Area-003", "이미 존재하는 구역 이름입니다."),

    //404 NOTFOUND
    AREA_NOT_FOUND(HttpStatus.NOT_FOUND, "Area-002", "구역을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    AreaExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
