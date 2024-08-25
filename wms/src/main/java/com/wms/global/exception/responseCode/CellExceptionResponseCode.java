package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum CellExceptionResponseCode {
    //409 CONFLICT
    CELL_CODE_DUPLICATE(HttpStatus.CONFLICT, "Cell-001", "셀 코드가 이미 존재합니다."),
    CELL_NAME_DUPLICATE(HttpStatus.CONFLICT, "Cell-003", "이미 존재하는 샐 이름입니다."),

    //404 NOTFOUND
    CELL_NOT_FOUND(HttpStatus.CONFLICT, "Cell-002", "셀을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    CellExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
