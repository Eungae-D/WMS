package com.wms.domain.inputWarehouseDetail.controller;

import com.wms.domain.inputWarehouseDetail.dto.response.InputWarehouseDetailsResponseDTO;
import com.wms.domain.inputWarehouseDetail.service.InputWarehouseDetailService;
import com.wms.global.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/inputWarehouseDetail")
public class InputWarehouseDetailController {

    private final InputWarehouseDetailService inputWarehouseDetailService;

    // 입고서 상세 조회
    @GetMapping("/{inputWarehouseId}")
    public ResponseEntity<ApiResponse<?>> getInputWarehouseDetails(@PathVariable Long inputWarehouseId) {
        List<InputWarehouseDetailsResponseDTO> inputWarehouseDetails = inputWarehouseDetailService.getInputWarehouseDetails(inputWarehouseId);
        return ResponseEntity.ok(ApiResponse.createSuccess(inputWarehouseDetails, "입고서 상세 조회 성공"));
    }
}
