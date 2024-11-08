package com.wms.global.exception.responseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum S3ExceptionResponseCode {
    UPLOAD_EXCEPTION(HttpStatus.BAD_REQUEST, "S3-001", "S3 업로드 중 에러가 발생하였습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    S3ExceptionResponseCode(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
