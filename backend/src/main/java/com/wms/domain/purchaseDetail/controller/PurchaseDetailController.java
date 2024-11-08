package com.wms.domain.purchaseDetail.controller;

import com.wms.domain.purchaseDetail.dto.response.PurchaseSheetDetailsResponseDTO;
import com.wms.domain.purchaseDetail.service.PurchaseDetailService;
import com.wms.global.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/purchaseDetail")
public class PurchaseDetailController {
    private final PurchaseDetailService purchaseDetailService;

    // 발주서 상세 조회
    @GetMapping("/{purchaseSheetId}")
    public ResponseEntity<ApiResponse<?>> getPurchaseSheetDetails(@PathVariable Long purchaseSheetId) {
        PurchaseSheetDetailsResponseDTO purchaseSheetDetails = purchaseDetailService.getPurchaseSheetDetails(purchaseSheetId);
        return ResponseEntity.ok(ApiResponse.createSuccess(purchaseSheetDetails, "발주서 상세 조회 성공"));
    }
}
