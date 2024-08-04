package com.wms.domain.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserExceptionResponseCode {


    //409 CONFLICT
    EXISTS_USER(HttpStatus.CONFLICT, "User-001", "이미 존재하는 유저입니다."),

    //404 NOT FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User-002", "유저를 찾지 못했습니다."),

    //403 FORBIDDEN
    FORBIDDEN_CLIENT(HttpStatus.FORBIDDEN, "User-003", "접근 권한이 없습니다."),

    //409 CONFLICT
    USER_EXISTS_IN_DEPARTMENT_AND_POSITION(HttpStatus.CONFLICT, "User-004", "이름, 부서, 직급이 같은 사용자가 이미 존재합니다.");






    private final HttpStatus status;
    private final String code;
    private final String message;

    UserExceptionResponseCode(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
