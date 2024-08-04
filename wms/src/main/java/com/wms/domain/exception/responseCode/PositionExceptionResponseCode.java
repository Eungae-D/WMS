package com.wms.domain.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum PositionExceptionResponseCode {

    //409 CONFLICT
    POSITION_CODE_DUPLICATE(HttpStatus.CONFLICT, "Position-001", "직급 코드가 이미 존재합니다."),

    //404 NOT_FOUND
    POSITION_NOT_FOUND(HttpStatus.NOT_FOUND, "Position-002", "직급을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    PositionExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
