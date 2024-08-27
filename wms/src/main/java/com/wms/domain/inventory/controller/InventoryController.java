package com.wms.domain.inventory.controller;

import com.wms.domain.area.dto.response.AreaResponseDTO;
import com.wms.domain.area.service.AreaService;
import com.wms.domain.cell.dto.response.CellResponseDTO;
import com.wms.domain.cell.service.CellService;
import com.wms.domain.inventory.dto.request.InventoryRequestDTO;
import com.wms.domain.inventory.dto.response.FlatStructureResponseDTO;
import com.wms.domain.inventory.dto.response.InventoryResponseDTO;
import com.wms.domain.inventory.service.InventoryService;
import com.wms.domain.rack.dto.response.RackResponseDTO;
import com.wms.domain.rack.service.RackService;
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
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    private final WarehouseService warehouseService;
    private final AreaService areaService;
    private final RackService rackService;
    private final CellService cellService;

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

    // 평면 구조 데이터 조회
    @GetMapping("/info")
    public ResponseEntity<ApiResponse<?>> getFlatStructureInfo() {
        List<WarehouseResponseDTO> warehouses = warehouseService.getAllWarehouses();
        List<AreaResponseDTO> areas = areaService.getAllAreas();
        List<RackResponseDTO> racks = rackService.getAllRacks();
        List<CellResponseDTO> cells = cellService.getAllCells();

        FlatStructureResponseDTO response = FlatStructureResponseDTO.builder()
                .warehouses(warehouses)
                .areas(areas)
                .racks(racks)
                .cells(cells)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.createSuccess(response, "평면 구조 데이터 조회 성공"));
    }
}
