package com.wms.domain.inputWarehouseDetail.controller;

import com.wms.domain.inputWarehouseDetail.dto.response.InputWarehouseDetailsResponseDTO;
import com.wms.domain.inputWarehouseDetail.dto.response.InputWarehouseDetailsResponseDTO2;
import com.wms.domain.inputWarehouseDetail.service.InputWarehouseDetailService;
import com.wms.global.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/inputWarehouseDetail")
public class InputWarehouseDetailController {

    private final InputWarehouseDetailService inputWarehouseDetailService;

    // 입고 ID로 입고 상세 정보 조회
    @GetMapping("/{inputWarehouseId}")
    public ResponseEntity<ApiResponse<?>> getInputWarehouseDetails(@PathVariable Long inputWarehouseId) {
        List<InputWarehouseDetailsResponseDTO> inputWarehouseDetails = inputWarehouseDetailService.getInputWarehouseDetails(inputWarehouseId);
        return ResponseEntity.ok(ApiResponse.createSuccess(inputWarehouseDetails, "입고서 상세 조회 성공"));
    }

    // 발주서 ID로 입고 상세 정보 조회
    @GetMapping("/byPurchaseSheet/{purchaseSheetId}")
    public ResponseEntity<ApiResponse<?>> getInputWarehouseDetailsByPurchaseSheetId(@PathVariable Long purchaseSheetId) {
        List<InputWarehouseDetailsResponseDTO2> inputWarehouseDetails = inputWarehouseDetailService.getInputWarehouseDetailsByPurchaseSheetId(purchaseSheetId);
        return ResponseEntity.ok(ApiResponse.createSuccess(inputWarehouseDetails, "입고 상세 정보 조회 성공"));
    }

    // 입고 완료
    @PostMapping("/complete")
    public ResponseEntity<ApiResponse<?>> completeInputWarehouse(@RequestBody List<Long> inputWarehouseDetailIds) {
        inputWarehouseDetailService.completeInputWarehouse(inputWarehouseDetailIds);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("입고 완료 처리 성공"));
    }
}
