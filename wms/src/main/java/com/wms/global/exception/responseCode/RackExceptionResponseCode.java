package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum RackExceptionResponseCode {
    //409 CONFLICT
    RACK_CODE_DUPLICATE(HttpStatus.CONFLICT, "Rack-001", "랙 코드가 이미 존재합니다."),
    RACK_NAME_DUPLICATE(HttpStatus.CONFLICT, "Rack-003", "이미 존재하는 랙 이름입니다."),

    //404 NOTFOUND
    RACK_NOT_FOUND(HttpStatus.CONFLICT, "Rack-002", "랙을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    RackExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
