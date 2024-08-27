package com.wms.domain.inventory.controller;

import com.wms.domain.inventory.dto.request.InventoryRequestDTO;
import com.wms.domain.inventory.dto.response.InventoryResponseDTO;
import com.wms.domain.inventory.service.InventoryService;
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
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    // 재고 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerInventory(@Valid @RequestBody List<InventoryRequestDTO> inventoryRequestDTOList) {
        inventoryService.registerInventoryList(inventoryRequestDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("제품 초기 등록 성공."));
    }

    // 전체 재고 목록 조회
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllInventory() {
        List<InventoryResponseDTO> inventoryList = inventoryService.getAllInventory();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(inventoryList, "전체 재고 목록 조회 성공"));
    }
}
