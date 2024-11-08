package com.wms.domain.orderSheet.controller;

import com.wms.domain.orderSheet.dto.request.OrderSheetRequestDTO;
import com.wms.domain.orderSheet.dto.response.OrderSheetResponseDTO;
import com.wms.domain.orderSheet.service.OrderSheetService;
import com.wms.global.util.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 수주서 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllOrderSheets() {
        List<OrderSheetResponseDTO> orderSheetList = orderSheetService.getAllOrderSheets();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(orderSheetList, "수주서 목록 가져오기 성공"));
    }

    // 수주서 삭제
    @DeleteMapping("/delete/{orderSheetId}")
    public ResponseEntity<ApiResponse<?>> deleteOrderSheet(@PathVariable Long orderSheetId) {
        orderSheetService.deleteOrderSheet(orderSheetId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.createSuccessNoContent("수주서 삭제 성공"));
    }
}
