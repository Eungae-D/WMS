package com.wms.domain.exception;

import com.wms.domain.exception.exception.TokenException;
import com.wms.domain.exception.exception.UserException;
import com.wms.global.util.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //유저 예외 핸들러
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse<?>> handleUserException(UserException e, HttpServletRequest request){

        log.error("요청 경로 : { }, 실패 이유 : { }, 로그 : { }", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    //토큰 예외 핸들러
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiResponse<?>> handleTokenException(UserException e, HttpServletRequest request){

        log.error("요청 경로 : { }, 실패 이유 : { }, 로그 : { }", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }
}
