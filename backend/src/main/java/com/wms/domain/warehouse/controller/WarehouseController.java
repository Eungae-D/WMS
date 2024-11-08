package com.wms.domain.warehouse.controller;

import com.wms.domain.warehouse.dto.request.WarehouseRequestDTO;
import com.wms.domain.warehouse.dto.response.WarehouseResponseDTO;
import com.wms.domain.warehouse.service.WarehouseService;
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
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    // 창고 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerWarehouse(@Valid @RequestBody WarehouseRequestDTO warehouseRequestDTO) {
        warehouseService.registerWarehouse(warehouseRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("창고 등록 성공."));
    }

    // 창고 삭제
    @DeleteMapping("/delete/{warehouseId}")
    public ResponseEntity<ApiResponse<?>> deleteWarehouse(@PathVariable Long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccessNoContent("창고 삭제 성공."));
    }

    // 창고 목록 가져오기
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllWarehouses() {
        List<WarehouseResponseDTO> warehouseList = warehouseService.getAllWarehouses();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(warehouseList, "창고 목록 가져오기 성공."));
    }

    // 창고 목록 가져오기(창고 코드로 검색)
    @GetMapping("/code/{warehouseCode}")
    public ResponseEntity<ApiResponse<?>> getWarehousesByCode(@PathVariable String warehouseCode) {
        List<WarehouseResponseDTO> warehouseList = warehouseService.getWarehousesByCode(warehouseCode);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(warehouseList, "창고 목록 가져오기 성공.(코드 검색)"));
    }

    // 창고 목록 가져오기(창고명으로 검색)
    @GetMapping("/name/{warehouseName}")
    public ResponseEntity<ApiResponse<?>> getWarehousesByName(@PathVariable String warehouseName) {
        List<WarehouseResponseDTO> warehouseList = warehouseService.getWarehousesByName(warehouseName);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(warehouseList, "창고 목록 가져오기 성공.(이름 검색)"));
    }
}
