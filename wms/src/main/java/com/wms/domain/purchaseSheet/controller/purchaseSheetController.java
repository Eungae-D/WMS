package com.wms.domain.purchaseSheet.controller;

import com.wms.domain.purchaseSheet.dto.request.PurchaseSheetRequestDTO;
import com.wms.domain.purchaseSheet.dto.response.PurchaseSheetResponseDTO;
import com.wms.domain.purchaseSheet.service.PurchaseSheetService;
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
@RequestMapping("/api/v1/purchaseSheet")
public class purchaseSheetController {

    private final PurchaseSheetService purchaseSheetService;

    // 발주서 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerPurchaseSheet(@Valid @RequestBody PurchaseSheetRequestDTO purchaseSheetRequestDTO) {
        purchaseSheetService.createPurchaseSheet(purchaseSheetRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("발주서 등록 성공"));
    }

    // 발주서 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllPurchaseSheets() {
        List<PurchaseSheetResponseDTO> purchaseSheetList = purchaseSheetService.getAllPurchaseSheets();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(purchaseSheetList, "발주서 목록 가져오기 성공"));
    }

}
