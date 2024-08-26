package com.wms.domain.inventory.controller;

import com.wms.domain.inventory.dto.request.InventoryRequestDTO;
import com.wms.domain.inventory.service.InventoryService;
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
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    // 재고 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerInventory(@Valid @RequestBody InventoryRequestDTO inventoryRequestDTO) {
        inventoryService.registerInventory(inventoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("제품 초기 등록 성공."));
    }
}
