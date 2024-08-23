package com.wms.global.exception;

import com.wms.global.exception.exception.DepartmentException;
import com.wms.global.exception.exception.PositionException;
import com.wms.global.exception.exception.TokenException;
import com.wms.global.exception.exception.UserException;
import com.wms.global.util.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //유효성검사 예외 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("요청 경로: {}, 실패 이유: {}, 로그: {}", request.getRequestURI(), "VALIDATION_ERROR", errorMessage);
        return ResponseEntity.badRequest().body(ApiResponse.createError("VALIDATION_ERROR", errorMessage));
    }

    //유저 예외 핸들러
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse<?>> handleUserException(UserException e, HttpServletRequest request){

        log.error("요청 경로 : { }, 실패 이유 : { }, 로그 : { }", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    //토큰 예외 핸들러
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiResponse<?>> handleTokenException(TokenException e, HttpServletRequest request){

        log.error("요청 경로 : { }, 실패 이유 : { }, 로그 : { }", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    //부서 예외 핸들러
    @ExceptionHandler(DepartmentException.class)
    public ResponseEntity<ApiResponse<?>> handleDepartmentException(DepartmentException e, HttpServletRequest request){

        log.error("요청 경로 : { }, 실패 이유 : { }, 로그 : { }", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    //직급 예외 핸들러
    @ExceptionHandler(PositionException.class)
    public ResponseEntity<ApiResponse<?>> handlePositionException(PositionException e, HttpServletRequest request){

        log.error("요청 경로 : { }, 실패 이유 : { }, 로그 : { }", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }
}
