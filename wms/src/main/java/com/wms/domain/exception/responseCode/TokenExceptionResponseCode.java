package com.wms.domain.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TokenExceptionResponseCode {

    //400 BAD REQUEST
    REFRESH_TOKEN_NULL(HttpStatus.BAD_REQUEST, "Token-001", "refreshToken이 null 입니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "Token-002", "refreshToken이 만료되었습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "Token-003", "유효하지 않은 토큰입니다."),
    DOES_NOT_MATCH_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "Token-004", "refreshToken이 일치하지 않습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;

    TokenExceptionResponseCode(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
