package com.wms.global.exception;

import com.wms.global.exception.exception.*;
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

    // 유효성검사 예외 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("요청 경로: {}, 실패 이유: {}, 로그: {}", request.getRequestURI(), "VALIDATION_ERROR", errorMessage);
        return ResponseEntity.badRequest().body(ApiResponse.createError("VALIDATION_ERROR", errorMessage));
    }

    // 유저 예외 핸들러
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse<?>> handleUserException(UserException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 토큰 예외 핸들러
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiResponse<?>> handleTokenException(TokenException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 부서 예외 핸들러
    @ExceptionHandler(DepartmentException.class)
    public ResponseEntity<ApiResponse<?>> handleDepartmentException(DepartmentException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 직급 예외 핸들러
    @ExceptionHandler(PositionException.class)
    public ResponseEntity<ApiResponse<?>> handlePositionException(PositionException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 거래처 예외 핸들러
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ApiResponse<?>> handleClientException(ClientException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 상품 예외 핸들러
    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ApiResponse<?>> handleItemException(ItemException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 창고 예외 핸들러
    @ExceptionHandler(WarehouseException.class)
    public ResponseEntity<ApiResponse<?>> handleWarehouseException(WarehouseException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 구역 예외 핸들러
    @ExceptionHandler(AreaException.class)
    public ResponseEntity<ApiResponse<?>> handleAreaException(AreaException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 랙 예외 핸들러
    @ExceptionHandler(RackException.class)
    public ResponseEntity<ApiResponse<?>> handleRackException(RackException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 셀 예외 핸들러
    @ExceptionHandler(CellException.class)
    public ResponseEntity<ApiResponse<?>> handleCellException(CellException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 로트 예외 핸들러
    @ExceptionHandler(LotException.class)
    public ResponseEntity<ApiResponse<?>> handleLotException(LotException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 재고 예외 핸들러
    @ExceptionHandler(InventoryException.class)
    public ResponseEntity<ApiResponse<?>> handleInventoryException(InventoryException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 수주서 예외 핸들러
    @ExceptionHandler(OrderSheetException.class)
    public ResponseEntity<ApiResponse<?>> handleOrderSheetException(OrderSheetException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 발주서 예외 핸들러
    @ExceptionHandler(PurchaseSheetException.class)
    public ResponseEntity<ApiResponse<?>> handlePurchaseSheetException(PurchaseSheetException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }


    // 발주서 상세 예외 핸들러
    @ExceptionHandler(PurchaseDetailException.class)
    public ResponseEntity<ApiResponse<?>> handlePurchaseDetailException(PurchaseDetailException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

    // 입고서 상세 예외 핸들러
    @ExceptionHandler(InputWarehouseDetailException.class)
    public ResponseEntity<ApiResponse<?>> handleInputWarehouseDetailException(InputWarehouseDetailException e, HttpServletRequest request){

        log.error("요청 경로 : {}, 실패 이유 : {}, 로그 : {}", request.getRequestURI(), e.getExceptionCode().getCode(), e.getLog());

        return ResponseEntity.status(e.getExceptionCode().getStatus()).body(ApiResponse.createError(e.getExceptionCode().getCode(), e.getExceptionCode().getMessage()));
    }

}

