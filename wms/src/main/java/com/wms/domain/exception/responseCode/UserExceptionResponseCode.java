package com.wms.domain.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserExceptionResponseCode {


    //409 NOT FOUND
    EXISTS_USER(HttpStatus.CONFLICT, "U-001", "이미 존재하는 유저입니다."),

    //404 NOT FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U-002", "유저를 찾지 못했습니다."),

    //403 FORBIDDEN
    FORBIDDEN_CLIENT(HttpStatus.FORBIDDEN, "U-003", "접근 권한이 없습니다.");



    private final HttpStatus status;
    private final String code;
    private final String message;

    UserExceptionResponseCode(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
