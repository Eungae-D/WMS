package com.wms.domain.inputWarehouse.controller;

import com.wms.domain.inputWarehouse.dto.request.InputWarehouseRequestDTO;
import com.wms.domain.inputWarehouse.dto.response.InputWarehouseResponseDTO;
import com.wms.domain.inputWarehouse.service.InputWarehouseService;
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
@RequestMapping("/api/v1/inputWarehouse")
public class InputWarehouseController {
    private final InputWarehouseService inputWarehouseService;

    // 입고 등록
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerInputWarehouse(@Valid @RequestBody InputWarehouseRequestDTO inputWarehouseRequestDTO) {
        inputWarehouseService.createInputWarehouse(inputWarehouseRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.createSuccessNoContent("입고 등록 성공"));
    }

    // 입고 목록 조회
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> getInputWarehouseList() {
        List<InputWarehouseResponseDTO> inputWarehouseList = inputWarehouseService.getInputWarehouseList();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(inputWarehouseList, "입고 목록 조회 성공"));
    }

}
