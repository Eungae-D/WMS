package com.wms.domain.warehouse.service;

import com.wms.domain.warehouse.dto.request.WarehouseRequestDTO;
import com.wms.domain.warehouse.dto.response.WarehouseResponseDTO;
import com.wms.domain.warehouse.entity.Warehouse;
import jakarta.validation.Valid;

import java.util.List;

public interface WarehouseService {
    // 창고 등록
    void registerWarehouse(@Valid WarehouseRequestDTO warehouseRequestDTO);

    // 창고 삭제
    void deleteWarehouse(Long warehouseId);

    // 창고 목록 가져오기
    List<WarehouseResponseDTO> getAllWarehouses();

    // 창고 목록 가져오기(창고 코드)
    List<WarehouseResponseDTO> getWarehousesByCode(String code);

    // 창고 목록 가져오기(창고명)
    List<WarehouseResponseDTO> getWarehousesByName(String name);
}
