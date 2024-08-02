package com.wms.domain.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum DepartmentExceptionResponseCode {

    //409 CONFLICT
    DEPARTMENT_CODE_DUPLICATE(HttpStatus.CONFLICT, "D-001", "부서 코드가 이미 존재합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    DepartmentExceptionResponseCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
