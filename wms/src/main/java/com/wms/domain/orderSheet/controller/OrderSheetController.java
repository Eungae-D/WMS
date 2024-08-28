package com.wms.domain.orderSheet.controller;

import com.wms.domain.orderSheet.dto.request.OrderSheetRequestDTO;
import com.wms.domain.orderSheet.service.OrderSheetService;
import com.wms.global.util.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orderSheet")
public class OrderSheetController {
    private final OrderSheetService orderSheetService;

    // 수주서 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerOrderSheet(@Valid @RequestBody OrderSheetRequestDTO orderSheetRequestDTO) {
        orderSheetService.createOrderSheet(orderSheetRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("수주서 등록 성공"));
    }
}
